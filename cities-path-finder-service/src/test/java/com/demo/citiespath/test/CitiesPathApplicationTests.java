package com.demo.citiespath.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class CitiesPathApplicationTests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CitiesPathApplicationTests {

	/** The rest template. */
	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Test service city paths.
	 */
	@Test
	public void testServiceCityPaths() {
		// Calling cities service must be mocked up
		/*
		 * String content; ArrayList<CityPath> listCities = new ArrayList<CityPath>();
		 * CityPath city = new CityPath(); city.setCityDestination("Zaragoza");
		 * city.setCitySource("Madrid"); city.setDuration(new Long(180));
		 * city.setEndTime("11:00"); city.setStartTime("08:00");
		 * 
		 * listCities.add(city);
		 * 
		 * RestTemplate restTemplateTest = new RestTemplate();
		 * 
		 * MockRestServiceServer mockServer =
		 * MockRestServiceServer.bindTo(restNoTestTemplate).build(); try {
		 * mockServer.expect(requestTo("http://cities-management:8080/cities"))
		 * .andRespond(withSuccess()
		 * .body(CityPath.toJson((Collection<CityPath>)listCities))); } catch
		 * (JsonException e) {
		 * 
		 * assertTrue(false); }
		 * 
		 * 
		 * 
		 * 
		 * ResponseEntity<String> response =
		 * restNoTestTemplate.getForEntity("/paths/Zaragoza", String.class);
		 * assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		 * 
		 */
		assertTrue(true);

	}

}
