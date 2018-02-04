package com.demo.citiesmanagement.domain;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

import com.demo.cities.domain.exceptions.JsonException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Class CityPath.
 */
public class CityPath {

	/** The id route. */
	@JsonProperty("id")
	private String id;

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

	/**
	 * Instantiates a new find paths request.
	 */
	public CityPath() {
	}

	/**
	 * Instantiates a new city path.
	 *
	 * @param origin
	 *            the origin
	 * @param destination
	 *            the destination
	 * @param duration
	 *            the duration
	 */
	public CityPath(String origin, String destination, int duration) {
		setCitySource(origin);
		setCityDestination(destination);
		setDuration(new Long(duration));
	}

	/**
	 * Gets the id route.
	 *
	 * @return the id route
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id route.
	 *
	 * @param idRoute
	 *            the new id route
	 */
	public void setId(String idRoute) {
		this.id = idRoute;
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
	 * Serialize CitiesManagementResponse POJO into JSON.
	 *
	 * @return the string
	 * @throws JsonException
	 *             - In case of error during serialization.
	 */
	public String toJson() throws JsonException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, this);
			return sw.toString();
		} catch (IOException e) {
			throw new JsonException("Error marshalling " + this, e);
		}
	}

	/**
	 * Serialize CitiesManagementResponse POJO into JSON.
	 *
	 * @param listCities
	 *            the list cities
	 * @return the string
	 * @throws JsonException
	 *             - In case of error during serialization.
	 */
	public static String toJson(Collection<CityPath> listCities) throws JsonException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		StringWriter sw = new StringWriter();
		try {
			mapper.writerFor(new TypeReference<Collection<CityPath>>() {
			}).writeValue(sw, listCities);
			return sw.toString();
		} catch (IOException e) {
			throw new JsonException("Error marshalling ", e);
		}
	}

	/**
	 * From json.
	 *
	 * @param json
	 *            the json
	 * @return the city path
	 * @throws JsonException
	 *             the json exception
	 */
	public static CityPath fromJson(String json) throws JsonException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			CityPath city = mapper.readValue(json, new TypeReference<CityPath>() {
			});
			return city;
		} catch (IOException e) {
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
		builder.append("CitiesManagementResponse [city source =").append(citySource).append(", city destination =")
				.append(cityDestination).append(", departure time =").append(startTime).append(", arrival time =")
				.append(endTime).append("]");
		return builder.toString();
	}

	/**
	 * Deserialize a Collection<CityPath> into JSON (array of elements).
	 *
	 * @param routesCollectionJson
	 *            the routes collection json
	 * @return the string
	 * @throws JsonException
	 *             - exception during serialization.
	 */
	public static Collection<CityPath> fromJsonCollection(String routesCollectionJson) throws JsonException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			List<CityPath> listOfRoutes = mapper.readValue(routesCollectionJson, new TypeReference<List<CityPath>>() {
			});
			return listOfRoutes;
		} catch (IOException e) {
			throw new JsonException("Error unmarshalling json: " + e);
		}
	}

}
