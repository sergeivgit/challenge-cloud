package com.demo.citiespath.domain;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import com.demo.cities.domain.exceptions.JsonException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Class CitiesPathResponse.
 */
public class CitiesPathResponse {

	/** The list city destinations. */
	ArrayList<CalculatedCitiesRoutes> listCityDestinations = new ArrayList<CalculatedCitiesRoutes>();

	/**
	 * Adds the routes.
	 *
	 * @param cityRoutes
	 *            the city routes
	 */
	public void addRoutes(CalculatedCitiesRoutes cityRoutes) {
		listCityDestinations.add(cityRoutes);
	}

	/**
	 * To json.
	 *
	 * @return the string
	 * @throws JsonException
	 *             the json exception
	 */
	public String toJson() throws JsonException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		StringWriter sw = new StringWriter();
		try {
			mapper.writerFor(new TypeReference<ArrayList<CalculatedCitiesRoutes>>() {
			}).writeValue(sw, listCityDestinations);
			return sw.toString();
		} catch (IOException e) {
			throw new JsonException("Error marshalling " + listCityDestinations, e);
		}
	}

}
