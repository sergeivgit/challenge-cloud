package com.demo.citiesmanagement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class CitiesManagementApplicationTests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CitiesManagementApplicationTests {

	/** The rest template. */
	@Autowired
	public TestRestTemplate restTemplate;
	
	/**
	 * Test add city path.
	 */
	@Test
	public void testAddCityPath() {
		//Calling cities service must be mocked somehow

		StringBuilder builderRequestJson = new StringBuilder();
		builderRequestJson.append("{").append("\n")
		.append("\"city\": \"Zaragoza\",").append("\n")
		.append("\"city_destination\": \"Barcelona\",").append("\n")
		.append("\"departure_time\": \"08:30\",").append("\n")
		.append("\"arrival_time\": \"11:45\"").append("\n")
		.append("}").append("\n");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(builderRequestJson.toString(),headers);
		ResponseEntity<String> queryResponse = restTemplate
				  .exchange("/cities", HttpMethod.POST, entity, String.class);
		assertTrue(queryResponse.getStatusCode() == HttpStatus.OK);

	}
	
	/**
	 * Test get city path.
	 */
	@Test
	public void testGetCityPath() {
		StringBuilder builderRequestJson = new StringBuilder();
		builderRequestJson.append("{").append("\n")
		.append("\"city\": \"Zaragoza\",").append("\n")
		.append("\"city_destination\": \"Barcelona\",").append("\n")
		.append("\"departure_time\": \"08:30\",").append("\n")
		.append("\"arrival_time\": \"11:45\"").append("\n")
		.append("}").append("\n");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(builderRequestJson.toString(),headers);
		ResponseEntity<String> queryResponse = restTemplate
				  .exchange("/cities", HttpMethod.POST, entity, String.class);

		ResponseEntity<String> response
		  = restTemplate.getForEntity("/cities/Zaragoza", String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

	}
}
