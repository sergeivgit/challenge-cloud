package com.demo.citiesmanagement.domain;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.cities.domain.exceptions.JsonException;
//import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class CitiesManagementRequest.
 */
public class CitiesManagementRequest {

	/** The id route. */
	@JsonProperty("id")
	private String idRoute;

	/** The city source. */
	@JsonProperty("city")
	private String citySource;

	/** The city destination. */
	@JsonProperty("city_destination")
	private String cityDestination;

	/** The start time. */
	@JsonProperty("departure_time")
	private String startTime;

	/** The end time. */
	@JsonProperty("arrival_time")
	private String endTime;

	/** The duration. */
	private Long duration;

	/** The Constant log. */
	private final static Logger log = LoggerFactory.getLogger(CitiesManagementRequest.class);

	/**
	 * Instantiates a new find paths request.
	 */
	public CitiesManagementRequest() {
	}

	/**
	 * Gets the id route.
	 *
	 * @return the id route
	 */
	public String getIdRoute() {
		return idRoute;
	}

	/**
	 * Sets the id route.
	 *
	 * @param idRoute
	 *            the new id route
	 */
	public void setIdRoute(String idRoute) {
		this.idRoute = idRoute;
	}

	/**
	 * Gets the city source.
	 *
	 * @return the city source
	 */
	public String getCitySource() {
		return citySource;
	}

	/**
	 * Sets the city source.
	 *
	 * @param citySource
	 *            the new city source
	 */
	public void setCitySource(String citySource) {
		this.citySource = citySource;
	}

	/**
	 * Gets the city destination.
	 *
	 * @return the city destination
	 */
	public String getCityDestination() {
		return cityDestination;
	}

	/**
	 * Sets the city destination.
	 *
	 * @param cityDestination
	 *            the new city destination
	 */
	public void setCityDestination(String cityDestination) {
		this.cityDestination = cityDestination;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime
	 *            the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime
	 *            the new end time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public Long getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration
	 *            the new duration
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}

	/**
	 * Deserialize Json into CitiesManagementRequest POJO.
	 *
	 * @param json
	 *            the json
	 * @return CitiesManagementRequest
	 * @throws JsonException
	 *             - In case of error during deserialization.
	 */
	public static CitiesManagementRequest fromJson(String json) throws JsonException {
		ObjectMapper mapper = new ObjectMapper();

		log.debug("CitiesManagementRequest.fromJson for json: " + json + " <<<<< ");

		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, CitiesManagementRequest.class);
		} catch (IOException e) {
			log.error("Error on CitiesManagementRequest.fromJson for json: " + json + " <<<<< " + e, e);
			throw new JsonException("Error unmarshalling json: " + e);
		}

	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CitiesPathRequest [city source =").append(citySource).append(", city destination =")
				.append(cityDestination).append(", departure time =").append(startTime).append(", arrival time =")
				.append(endTime).append("]");
		return builder.toString();
	}

}
