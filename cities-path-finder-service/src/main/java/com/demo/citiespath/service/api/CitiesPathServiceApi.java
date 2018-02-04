package com.demo.citiespath.service.api;

import com.demo.cities.domain.exceptions.JsonException;
import com.demo.citiespath.domain.CitiesPathResponse;
import com.demo.citiespath.service.exceptions.CitiesPathException;

/**
 * The Interface CitiesPathServiceApi.
 */
public interface CitiesPathServiceApi {

	/**
	 * Gets the paths.
	 *
	 * @param cityRequest
	 *            the city request
	 * @return the paths
	 * @throws CitiesPathException
	 *             the cities path exception
	 * @throws JsonException
	 *             the json exception
	 */
	public CitiesPathResponse getPaths(String cityRequest) throws CitiesPathException, JsonException;

}
