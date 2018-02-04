package com.demo.citiesmanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Entity Class CityRoute to store a city route in the 
 * database
 * 
 */
@Entity
public class CityRoute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6652240893074133186L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The city. */
	@Column(name = "city_origin", nullable = false)
	private String city;

	/** The destination city. */
	@Column(name = "city_destination", nullable = false)
	private String destinationCity;

	/** The departure time. */
	@Column(name = "departure_time", nullable = false)
	private String departureTime;

	/** The arrival time. */
	@Column(name = "arrival_time", nullable = false)
	private String arrivalTime;

	/** The duration. */
	@Column(name = "duration", nullable = false)
	private Long duration;

	/**
	 * Instantiates a new city route.
	 */
	protected CityRoute() {
	}

	/**
	 * Instantiates a new city route.
	 *
	 * @param cityOrigin
	 *            the city origin
	 * @param cityDestination
	 *            the city destination
	 * @param departureTime
	 *            the departure time
	 * @param arrivalTime
	 *            the arrival time
	 * @param duration
	 *            the duration
	 */
	public CityRoute(String cityOrigin, String cityDestination, String departureTime, String arrivalTime,
			Long duration) {
		this.city = cityOrigin;
		this.destinationCity = cityDestination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the destination city.
	 *
	 * @return the destination city
	 */
	public String getDestinationCity() {
		return destinationCity;
	}

	/**
	 * Sets the destination city.
	 *
	 * @param destinationCity
	 *            the new destination city
	 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	/**
	 * Gets the departure time.
	 *
	 * @return the departure time
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * Sets the departure time.
	 *
	 * @param departureTime
	 *            the new departure time
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * Gets the arrival time.
	 *
	 * @return the arrival time
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the arrival time.
	 *
	 * @param arrivalTime
	 *            the new arrival time
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format(
				"Customer[id=%d, city origin='%s', city destination='%s', departure time='%s', arrival time='%s', route duration'%s']",
				id, city, destinationCity, departureTime, arrivalTime, duration);
	}

}
