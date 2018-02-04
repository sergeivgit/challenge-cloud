package com.demo.cities.utils.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.demo.cities.domain.exceptions.JsonException;
import com.demo.citiesmanagement.domain.CityPath;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Test city path creation from J son.
	 */
	@Test
	public void testCityPathCreationFromJSon() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n").append("\"duration\" : 180,").append("\n").append("\"city\" : \"Zaragoza\",").append("\n")
				.append("\"city_destination\" : \"Barcelona\",").append("\n").append("\"departure_time\" : \"08:35\",")
				.append("\n").append("\"arrival_time\" : \"11:35\"").append("\n").append("}").append("\n");

		CityPath city = new CityPath();
		try {
			city = CityPath.fromJson(builder.toString());
		} catch (JsonException e) {
			assertTrue(false);
		}
		assertTrue(city.getCitySource().equalsIgnoreCase("Zaragoza")
				&& city.getCityDestination().equalsIgnoreCase("Barcelona") && city.getDuration().longValue() == 180);
	}

	/**
	 * Test city path to J son.
	 */
	@Test
	public void testCityPathToJSon() {
		CityPath cityPath = new CityPath();
		cityPath.setCityDestination("Barcelona");
		cityPath.setCitySource("Zaragoza");
		cityPath.setDuration(new Long(180));
		cityPath.setStartTime("08:35");
		cityPath.setEndTime("11:35");
		//cityPath.setIdRoute("1");
		String json = null;
		try {
			json = cityPath.toJson();
		} catch (JsonException e) {
			assertTrue(false);
			return;
		}

		assertTrue(json.length() > 0);
	}
}
