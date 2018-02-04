package com.demo.citiespath;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The Class CitiesPathApplication.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
public class CitiesPathApplication {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CitiesPathApplication.class, args);
	}
}
