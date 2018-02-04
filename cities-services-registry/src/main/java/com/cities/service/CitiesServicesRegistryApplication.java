package com.cities.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CitiesServicesRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitiesServicesRegistryApplication.class, args);
	}
}
