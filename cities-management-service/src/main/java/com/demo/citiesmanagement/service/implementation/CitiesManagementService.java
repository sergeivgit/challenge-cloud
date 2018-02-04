package com.demo.citiesmanagement.service.implementation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.citiesmanagement.domain.CitiesManagementRequest;
import com.demo.citiesmanagement.domain.CitiesManagementResponse;
import com.demo.citiesmanagement.domain.CityRoute;
import com.demo.citiesmanagement.exceptions.CitiesManagementException;
import com.demo.citiesmanagement.exceptions.CitiesManagementException.Reason;
import com.demo.citiesmanagement.repository.CityRoutesRepository;
import com.demo.citiesmanagement.service.api.CitiesManagementServiceApi;


/**
 * The Class CitiesManagementService.
 */
@Service
public class CitiesManagementService implements CitiesManagementServiceApi {

	/** The city routes repo. */
	@Autowired
	private CityRoutesRepository cityRoutesRepo;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demo.citiesmanagement.service.api.CitiesManagementServiceApi#addCityRoute
	 * (com.demo.citiesmanagement.domain.CitiesManagementRequest)
	 */
	@Override
	public CitiesManagementResponse addCityRoute(CitiesManagementRequest cityRequest) throws CitiesManagementException {
		// Checks data in the request is valid
		if (checkCityRequestData(cityRequest)) {
			long duration;
			try {
				// Calculate duration for later path calculation algorithms
				duration = getDurationCityTimesRequest(cityRequest);
			} catch (Exception ex) {
				// The arrival or departure times are not in the right format HH:MM
				throw new CitiesManagementException("Invalid times data in request", Reason.BadDepartureArrivalTime);
			}

			if (duration >= 0) {
				// We got a good duration calculation
				CityRoute route = new CityRoute(cityRequest.getCitySource(), cityRequest.getCityDestination(),
						cityRequest.getStartTime(), cityRequest.getEndTime(), duration);
				
				// We store the data in the database
				CityRoute route_created;
				try {
					route_created = this.cityRoutesRepo.save(route);
				} catch (Exception e) {
					throw new CitiesManagementException("Data could not be saved in database.", Reason.DataError);
				}
				CitiesManagementResponse response = new CitiesManagementResponse(route_created);
				return response;
			} else {
				// Negative duration means arrival date is before departure time
				throw new CitiesManagementException("Arrival time must be after departure time",
						Reason.BadDepartureArrivalTimeFormat);
			}

		} else {
			// Request data, city origin or city destination are not valid, for example are
			// empty
			throw new CitiesManagementException("Invalid data in request", Reason.NoText);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demo.citiesmanagement.service.api.CitiesManagementServiceApi#addCityRoute
	 * (com.demo.citiesmanagement.domain.CitiesManagementRequest)
	 */
	@Override
	public CitiesManagementResponse updateCityRoute(CitiesManagementRequest cityRequest) throws CitiesManagementException {
		// Checks data in the request is valid
		if (checkCityRequestData(cityRequest)) {
			long duration;
			try {
				// Calculate duration for later path calculation algorithms
				duration = getDurationCityTimesRequest(cityRequest);
			} catch (Exception ex) {
				// The arrival or departure times are not in the right format HH:MM
				throw new CitiesManagementException("Invalid times data in request", Reason.BadDepartureArrivalTime);
			}

			if (duration >= 0) {
				// We got a good duration calculation
				CityRoute route = new CityRoute(cityRequest.getCitySource(), cityRequest.getCityDestination(),
						cityRequest.getStartTime(), cityRequest.getEndTime(), duration);
				Long id;
				try {
					id = Long.decode(cityRequest.getIdRoute());
					route.setId(id);					
				} catch (Exception e) {
					throw new CitiesManagementException("Data could not be saved in database.", Reason.DataError);
				}

				// We store the data in the database
				CityRoute route_created;
				try {
					route_created = this.cityRoutesRepo.save(route);
				} catch (Exception e) {
					throw new CitiesManagementException("Data could not be saved in database.", Reason.DataError);
				}
				CitiesManagementResponse response = new CitiesManagementResponse(route_created);
				return response;
			} else {
				// Negative duration means arrival date is before departure time
				throw new CitiesManagementException("Arrival time must be after departure time",
						Reason.BadDepartureArrivalTimeFormat);
			}

		} else {
			// Request data, city origin or city destination are not valid, for example are
			// empty
			throw new CitiesManagementException("Invalid data in request", Reason.NoText);
		}
	}	

	/**
	 * Gets the duration city times request.
	 *
	 * @param cityRequest
	 *            the city request
	 * @return the duration city times request
	 */
	private long getDurationCityTimesRequest(CitiesManagementRequest cityRequest) {

		StringBuilder builder = new StringBuilder("2018-01-29 ");
		builder.append(cityRequest.getStartTime());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localStartDateTime = LocalDateTime.parse(builder.toString(), formatter);

		StringBuilder builder2 = new StringBuilder("2018-01-29 ");
		builder2.append(cityRequest.getEndTime());

		LocalDateTime localArrivalDateTime = LocalDateTime.parse(builder2.toString(), formatter);

		Duration duration = Duration.between(localStartDateTime, localArrivalDateTime);

		return duration.toMinutes();

	}

	/**
	 * This function will validate consistency data in the request. For example,
	 * ending time can't be less than starting time.
	 *
	 * @param cityRequest
	 *            the city request
	 * @return true, if successful
	 */
	private boolean checkCityRequestData(CitiesManagementRequest cityRequest) {

		if (cityRequest.getCitySource().isEmpty())
			return false;
		if (cityRequest.getCityDestination().isEmpty())
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.citiesmanagement.service.api.CitiesManagementServiceApi#
	 * getCityRoutes(java.lang.String)
	 */
	@Override
	public Collection<CitiesManagementResponse> getCityRoutes(String city) throws CitiesManagementException {

		Iterable<CityRoute> routes = this.cityRoutesRepo.findRoutesByCity(city);
		if (routes == null || !routes.iterator().hasNext()) {
			throw new CitiesManagementException("No routes found for city: " + city, Reason.CityNotFound);
		}

		ArrayList<CitiesManagementResponse> listResponses = new ArrayList<CitiesManagementResponse>();
		for (CityRoute cityRoute : routes) {
			listResponses.add(new CitiesManagementResponse(cityRoute));
		}

		return listResponses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demo.citiesmanagement.service.api.CitiesManagementServiceApi#delRoutes()
	 */
	@Override
	public CitiesManagementResponse delRoutes() {
		this.cityRoutesRepo.deleteAll();
		return new CitiesManagementResponse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.citiesmanagement.service.api.CitiesManagementServiceApi#
	 * delCityRoutes(java.lang.String)
	 */
	@Override
	public Collection<CitiesManagementResponse> delCityRoutes(String city) throws CitiesManagementException {

		Iterable<CityRoute> routes = this.cityRoutesRepo.findRoutesByCity(city);
		if (routes == null || !routes.iterator().hasNext()) {
			throw new CitiesManagementException("No routes found for city: " + city, Reason.CityNotFound);
		}

		ArrayList<CitiesManagementResponse> listResponses = new ArrayList<CitiesManagementResponse>();
		for (CityRoute cityRoute : routes) {
			listResponses.add(new CitiesManagementResponse(cityRoute));
			this.cityRoutesRepo.delete(cityRoute);
		}
		return listResponses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.demo.citiesmanagement.service.api.CitiesManagementServiceApi#getAllRoutes
	 * ()
	 */
	@Override
	public Collection<CitiesManagementResponse> getAllRoutes() throws CitiesManagementException {

		Iterable<CityRoute> routes = this.cityRoutesRepo.findAll();

		if (routes == null || !routes.iterator().hasNext()) {
			throw new CitiesManagementException("No routes found.", Reason.NoRoutes);
		}

		ArrayList<CitiesManagementResponse> listResponses = new ArrayList<CitiesManagementResponse>();
		for (CityRoute cityRoute : routes) {
			listResponses.add(new CitiesManagementResponse(cityRoute));
		}

		return listResponses;
	}

}
