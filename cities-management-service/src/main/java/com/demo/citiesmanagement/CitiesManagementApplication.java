package com.demo.citiesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class CitiesManagementApplication {

	public static void main(String[] args) {
		 
		SpringApplication.run(CitiesManagementApplication.class, args);
	}
}
