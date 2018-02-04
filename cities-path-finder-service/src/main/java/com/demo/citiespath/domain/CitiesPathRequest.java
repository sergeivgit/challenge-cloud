package com.demo.citiespath.domain;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.cities.domain.exceptions.JsonException;
//import com.example.palindrome.service.JsonException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class CitiesPathRequest.
 */
public class CitiesPathRequest {

	/** The city source. */
	@JsonProperty("city")
	private String citySource;

	/** The user. */
	@JsonProperty("user")
	private String user;

	/** The Constant log. */
	private final static Logger log = LoggerFactory.getLogger(CitiesPathRequest.class);

	/**
	 * Instantiates a new find paths request.
	 */
	public CitiesPathRequest() {
	}

	/**
	 * Deserialize Json into CitiesPathRequest POJO.
	 *
	 * @param json
	 *            the json
	 * @return CheckPalindromeRequest
	 * @throws JsonException
	 *             - In case of error during deserialization.
	 */
	public static CitiesPathRequest fromJson(String json) throws JsonException {
		ObjectMapper mapper = new ObjectMapper();

		log.info("CitiesPathRequest.fromJson for json: " + json + " <<<<< ");

		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, CitiesPathRequest.class);
		} catch (IOException e) {
			log.error("Error on CitiesPathRequest.fromJson for json: " + json + " <<<<< " + e, e);
			throw new JsonException("Error unmarshalling json: " + e);
		} catch (Exception ex) {
			log.error("Error on CitiesPathRequest.fromJson for json: " + json + " <<<<< " + ex, ex);
			throw new JsonException("Error unmarshalling json: " + ex);
		}
	}

	/**
	 * get the city source of the paths.
	 *
	 * @return city source of the paths
	 */
	public String getCitySource() {
		return citySource;
	}

	/**
	 * set the city source for the paths search.
	 *
	 * @param citySource
	 *            the new city source
	 */
	public void setCitySource(String citySource) {
		this.citySource = citySource;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(String user) {
		this.user = user;
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
		builder.append("CitiesPathRequest [city source =").append(citySource).append(", user=").append(user)
				.append("]");
		return builder.toString();
	}

}
