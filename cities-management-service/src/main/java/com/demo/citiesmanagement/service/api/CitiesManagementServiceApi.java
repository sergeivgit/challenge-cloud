package com.demo.citiesmanagement.service.api;

import java.util.Collection;

import com.demo.citiesmanagement.domain.CitiesManagementRequest;
import com.demo.citiesmanagement.domain.CitiesManagementResponse;
import com.demo.citiesmanagement.exceptions.CitiesManagementException;


/**
 * The Interface CitiesManagementServiceApi.
 */
public interface CitiesManagementServiceApi {

	/**
	 * Adds the city route.
	 *
	 * @param cityRequest
	 *            the city request
	 * @return the cities management response
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	public CitiesManagementResponse addCityRoute(CitiesManagementRequest cityRequest) throws CitiesManagementException;

	
	/**
	 * Updates the city route.
	 *
	 * @param cityRequest
	 *            the city request
	 * @return the cities management response
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	public CitiesManagementResponse updateCityRoute(CitiesManagementRequest cityRequest) throws CitiesManagementException;
	/**
	 * Gets the city routes.
	 *
	 * @param city
	 *            the city
	 * @return the city routes
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	public Collection<CitiesManagementResponse> getCityRoutes(String city) throws CitiesManagementException;

	/**
	 * Del city routes.
	 *
	 * @param city
	 *            the city
	 * @return the collection
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	public Collection<CitiesManagementResponse> delCityRoutes(String city) throws CitiesManagementException;

	/**
	 * Gets all routes.
	 *
	 * @return the all routes
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	public Collection<CitiesManagementResponse> getAllRoutes() throws CitiesManagementException;

	/**
	 * Del routes.
	 *
	 * @return the cities management response
	 * @throws CitiesManagementException
	 *             the cities management exception
	 */
	public CitiesManagementResponse delRoutes() throws CitiesManagementException;;

}
