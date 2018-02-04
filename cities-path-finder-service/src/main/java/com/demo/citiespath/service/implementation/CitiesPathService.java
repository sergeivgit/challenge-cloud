package com.demo.citiespath.service.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.cities.domain.exceptions.JsonException;
import com.demo.citiesmanagement.domain.CityPath;
import com.demo.citiespath.calculation.PathCalculator;
import com.demo.citiespath.config.CitiesPathConfiguration;
import com.demo.citiespath.domain.CalculatedCitiesRoutes;
import com.demo.citiespath.domain.CitiesPathResponse;
import com.demo.citiespath.service.api.CitiesPathServiceApi;
import com.demo.citiespath.service.exceptions.CitiesPathException;
import com.demo.citiespath.service.exceptions.CitiesPathException.Reason;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

/**
 * The Class CitiesPathService.
 * 		Implementation of the service in charge of getting the shortest path lists 
 * 		for all the possible destinations from a city
 */
@Service
public class CitiesPathService implements CitiesPathServiceApi {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CitiesPathService.class);

	/** The app config. */
	@Autowired
	private CitiesPathConfiguration appConfig;
	
	/**  the cities management service to get all the available routes */
	@Autowired
	private EurekaClient eurekaClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.citiespath.service.api.CitiesPathServiceApi#getPaths(java.lang.
	 * String)
	 */
	@Override
	public CitiesPathResponse getPaths(String cityRequest) throws CitiesPathException, JsonException {

		RestTemplate restTemplate = new RestTemplate();
		if (cityRequest == null || cityRequest.trim().isEmpty())
			throw new CitiesPathException("No city informed.", Reason.CityNotFound);

		String resourceName = appConfig.getCitiesServiceEurekaName();
		String apiName = appConfig.getCitiesServiceAPI();
	
	    Application application;
	    InstanceInfo instanceInfo;
	    String hostname = "";
	    int port;		
		try {
		    application = eurekaClient.getApplication(resourceName);
		    instanceInfo = application.getInstances().get(0);
		    hostname = instanceInfo.getHostName();
		    port = instanceInfo.getPort();			
		} catch (Exception e) {
			log.error("Error getting cities management service from eureka: " + resourceName, e);
			throw new CitiesPathException("No data from cities service.", Reason.NoRoutes);
		}
		
		StringBuilder resourceUrl = new StringBuilder("http://");
		resourceUrl.append(hostname)
			.append(":").append(Integer.valueOf(port).toString())
			.append(apiName);
		log.info("Calling remote service " + resourceName);
	

		ResponseEntity<String> response = null;
		try {
			// Get all the routes so the graph can be created and the paths calculations can take place
			response = restTemplate.getForEntity(resourceUrl.toString(), String.class);
		} catch (Exception ex) {
			log.error("Error getting data from cities management service: " + resourceUrl, ex);
			throw new CitiesPathException("No data from cities service.", Reason.NoRoutes);
		}

		ArrayList<CityPath> listRoutes = (ArrayList<CityPath>) CityPath.fromJsonCollection(response.getBody());
		log.info("List routes from service " + listRoutes);

		// Look for all possible destinations
		Collection<String> listDestinations = new ArrayList<String>();
		HashMap<String, String> mapDiferentDestinations = new HashMap<String, String>();
		// boolean variable to check if the city requested does exist in the routes
		boolean cityRequestExists = false;
		for (CityPath cityPath : listRoutes) {
			if (cityPath.getCitySource().equalsIgnoreCase(cityRequest))
				cityRequestExists = true;
			if (!cityPath.getCityDestination().equalsIgnoreCase(cityRequest))
				mapDiferentDestinations.put(cityPath.getCityDestination(), cityPath.getCityDestination());
		}
		
		if (!cityRequestExists)
			throw new CitiesPathException("The city requested does not exist as departure city for any route.", Reason.CityNotFound);
		// list of cities destination
		listDestinations = mapDiferentDestinations.values();
		CitiesPathResponse pathResponse = new CitiesPathResponse();

		// Get the pathc calculator object
		PathCalculator calculator = new PathCalculator();

		ArrayList<ArrayList<CityPath>> listShortestJumps = new ArrayList<ArrayList<CityPath>>();
		ArrayList<ArrayList<CityPath>> listShortestPath = new ArrayList<ArrayList<CityPath>>();
		if (!listDestinations.isEmpty()) {
			// Create graph without weight for all the cities
			calculator.setNoWeightGraph(listRoutes);
			// Create weighted graph for all the cities, the weight is going to be the
			// connection duration in minutes
			calculator.setWeightGraph(listRoutes);
			ArrayList<CityPath> weightPath = null;
			ArrayList<CityPath> jumpPath = null;
			// We calculate shortest connections and time paths for all the posible
			// destinations
			for (String cityDestination : listDestinations) {
				// calculate shortest time path
				weightPath = (ArrayList<CityPath>) calculator.getWeightShortestPath(cityRequest, cityDestination,
						appConfig.getStrategy());
				// calculate shortest connections path
				jumpPath = (ArrayList<CityPath>) calculator.getShortestPath(cityRequest, cityDestination,
						appConfig.getStrategy());
				CalculatedCitiesRoutes calculatedRoutes = new CalculatedCitiesRoutes();
				calculatedRoutes.setCityDestination(cityDestination);
				calculatedRoutes.setCitySource(cityRequest);
				calculatedRoutes.setListOptimumTime(weightPath);
				calculatedRoutes.setListOptimumConnections(jumpPath);
				log.info("List routes shortest path Jumps: " + jumpPath);
				log.info("List routes shortest path time: " + weightPath);
				pathResponse.addRoutes(calculatedRoutes);
			}
		}

		return pathResponse;
	}

}
