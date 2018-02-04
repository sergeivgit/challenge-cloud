package com.demo.citiespath.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The Class CitiesPathConfiguration.
 */
@Component
@ConfigurationProperties("citiesPathConfiguration") // prefix citiesPathConfiguration, find citiesPathConfiguration.*
public class CitiesPathConfiguration {

	/** The strategy. */
	private String strategy;

	/** The cities service url. */
	private String citiesServiceEurekaName;

	/** The cities service api name . */
	private String citiesServiceAPI;
	

	// getters and setters

	/**
	 * Gets the strategy.
	 *
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * Sets the strategy.
	 *
	 * @param strategy
	 *            the new strategy
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * Gets the cities service registered name in service registry.
	 *
	 * @return the cities service registered name in service registry
	 */
	public String getCitiesServiceEurekaName() {
		return citiesServiceEurekaName;
	}

	/**
	 * Sets the cities service registered name in service registry.
	 *
	 * @param citiesServiceUrl
	 *            the new cities service registered name in service registry
	 */
	public void setCitiesServiceEurekaName(String citiesServiceEurekaName) {
		this.citiesServiceEurekaName = citiesServiceEurekaName;
	}

	public String getCitiesServiceAPI() {
		return citiesServiceAPI;
	}

	public void setCitiesServiceAPI(String citiesServiceAPI) {
		this.citiesServiceAPI = citiesServiceAPI;
	}

}
