package com.demo.citiespath.domain;

import java.util.ArrayList;

import com.demo.citiesmanagement.domain.CityPath;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CalculatedCitiesRoutes.
 */
public class CalculatedCitiesRoutes {

	/** The city source. */
	@JsonProperty("city-origin")
	private String citySource;

	/** The city destination. */
	@JsonProperty("city-destination")
	private String cityDestination;

	/** The list optimum connections. */
	@JsonProperty("shortest-connections-route")
	private ArrayList<CityPath> listOptimumConnections;

	/** The list optimum time. */
	@JsonProperty("shortest-time-route")
	private ArrayList<CityPath> listOptimumTime;

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
	 * Gets the list optimum connections.
	 *
	 * @return the list optimum connections
	 */
	public ArrayList<CityPath> getListOptimumConnections() {
		return listOptimumConnections;
	}

	/**
	 * Sets the list optimum connections.
	 *
	 * @param listOptimumConnections
	 *            the new list optimum connections
	 */
	public void setListOptimumConnections(ArrayList<CityPath> listOptimumConnections) {
		this.listOptimumConnections = listOptimumConnections;
	}

	/**
	 * Gets the list optimum time.
	 *
	 * @return the list optimum time
	 */
	public ArrayList<CityPath> getListOptimumTime() {
		return listOptimumTime;
	}

	/**
	 * Sets the list optimum time.
	 *
	 * @param listOptimumTime
	 *            the new list optimum time
	 */
	public void setListOptimumTime(ArrayList<CityPath> listOptimumTime) {
		this.listOptimumTime = listOptimumTime;
	}

}
