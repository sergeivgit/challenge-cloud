package com.demo.citiesmanagement.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.cities.domain.exceptions.JsonException;
import com.demo.citiesmanagement.domain.CitiesManagementRequest;
import com.demo.citiesmanagement.domain.CitiesManagementResponse;
import com.demo.citiesmanagement.exceptions.CitiesManagementException;
import com.demo.citiesmanagement.service.implementation.CitiesManagementService;


/**
 * The Class CitiesManagementResource.
 */
@Controller
@RequestMapping("/cities")
public class CitiesManagementResource {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CitiesManagementResource.class);

	/** The cities management service. */
	@Autowired
	private CitiesManagementService citiesManagementService;


	/**
	 * Adds the city route.
	 *
	 * @param request
	 *            the request
	 * @param json
	 *            the city route data
	 * @return the response entity
	 * @throws CitiesManagementException
	 *             the cities management exception
	 * @throws JsonException
	 *             the json exception
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<CitiesManagementResponse> addCityRoute(HttpServletRequest request, @RequestBody String json)
			throws CitiesManagementException, JsonException {
		log.info("Rest call [" + request.getRemoteHost() + request.getRequestURI() + " ]");
		log.info("Rest call body [" + json + "]");

		CitiesManagementResponse responseService = citiesManagementService
				.addCityRoute(CitiesManagementRequest.fromJson(json));
		ResponseEntity<CitiesManagementResponse> response = new ResponseEntity<CitiesManagementResponse>(
				responseService, HttpStatus.OK);
		return response;
	}
	
	/**
	 * Adds the city route.
	 *
	 * @param request
	 *            the request
	 * @param json
	 *            the city route data
	 * @return the response entity
	 * @throws CitiesManagementException
	 *             the cities management exception
	 * @throws JsonException
	 *             the json exception
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<CitiesManagementResponse> updateCityRoute(HttpServletRequest request, @RequestBody String json)
			throws CitiesManagementException, JsonException {
		log.info("Rest call [" + request.getRemoteHost() + request.getRequestURI() + " ]");
		log.info("Rest call body [" + json + "]");

		CitiesManagementResponse responseService = citiesManagementService
				.updateCityRoute(CitiesManagementRequest.fromJson(json));
		ResponseEntity<CitiesManagementResponse> response = new ResponseEntity<CitiesManagementResponse>(
				responseService, HttpStatus.OK);
		return response;
	}

	/**
	 * Gets the routes.
	 *
	 * @param request
	 *            the request
	 * @return the routes
	 * @throws JsonException
	 *             the json exception
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<String> getRoutes(HttpServletRequest request)
			throws JsonException, CitiesManagementException {
		log.info("Rest call [" + request.getRemoteHost() + request.getRequestURI() + " ]");
		log.info("Rest call GET getRoutes ");

		Collection<CitiesManagementResponse> listResponses = citiesManagementService.getAllRoutes();
		ResponseEntity<String> response = new ResponseEntity<String>(
				CitiesManagementResponse.toJsonCollection(listResponses), HttpStatus.OK);
		return response;

	}

	/**
	 * Gets the routes from the given city.
	 *
	 * @param request
	 *            the request
	 * @param city
	 *            the city source of routes
	 * @return the list of routes from city
	 * @throws JsonException
	 *             the json exception
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	@RequestMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<String> getRoutesFromCity(HttpServletRequest request, @PathVariable("city") String city)
			throws JsonException, CitiesManagementException {
		log.info("Rest call [" + request.getRemoteHost() + request.getRequestURI() + " ]");
		log.info("Rest call GET getRoutes for city " + city);
		Collection<CitiesManagementResponse> listResponses = citiesManagementService.getCityRoutes(city);
		ResponseEntity<String> response = new ResponseEntity<String>(
				CitiesManagementResponse.toJsonCollection(listResponses), HttpStatus.OK);
		return response;
	}

	/**
	 * Deletes the routes from the given city.
	 *
	 * @param request
	 *            the request
	 * @param city
	 *            the city source of routes
	 * @return OK or not OK
	 * @throws JsonException
	 *             the json exception
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	@RequestMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<String> delRoutesFromCity(HttpServletRequest request, @PathVariable("city") String city)
			throws JsonException, CitiesManagementException {
		log.info("Rest call [" + request.getRemoteHost() + request.getRequestURI() + " ]");
		log.info("Rest call DELETE all routes for city: " + city);
		Collection<CitiesManagementResponse> listResponses = citiesManagementService.delCityRoutes(city);
		ResponseEntity<String> response = new ResponseEntity<String>(
				CitiesManagementResponse.toJsonCollection(listResponses), HttpStatus.OK);
		return response;
	}

	/**
	 * Deletes all the routes.
	 *
	 * @param request
	 *            the request
	 * @return OK or not OK
	 * @throws JsonException
	 *             the json exception
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<String> delRoutes(HttpServletRequest request)
			throws JsonException, CitiesManagementException {
		log.info("Rest call [" + request.getRemoteHost() + request.getRequestURI() + " ]");
		log.info("Rest call DELETE all routes");
		CitiesManagementResponse resp = citiesManagementService.delRoutes();
		ResponseEntity<String> response = new ResponseEntity<String>(resp.toJson(), HttpStatus.OK);
		return response;
	}

}
