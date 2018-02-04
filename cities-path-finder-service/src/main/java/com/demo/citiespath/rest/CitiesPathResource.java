package com.demo.citiespath.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.cities.domain.exceptions.JsonException;
import com.demo.citiespath.domain.CitiesPathResponse;
import com.demo.citiespath.service.exceptions.CitiesPathException;
import com.demo.citiespath.service.implementation.CitiesPathService;

/**
 * The Class CitiesPathResource.
 * 		Manages HTTP requests.
 */
@Controller
@RequestMapping("/paths")
public class CitiesPathResource {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CitiesPathResource.class);

	/** The path service. */
	@Autowired
	private CitiesPathService pathService;

	/**
	 * Gets the cities path.
	 *
	 * @param request
	 *            the HTTP servlet request
	 * @param city
	 *            the source city to get the routes
	 * @return the list of routes to all destinations. For every destination
	 * 				two routes are obtained, one for shortest connections path 
	 * 				and the other for shortest time path
	 * @throws CitiesPathException
	 *             the cities path exception
	 * @throws JsonException
	 *             the json exception
	 */
	@RequestMapping(path = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<String> getCitiesPath(HttpServletRequest request, @PathVariable("city") String city)
			throws CitiesPathException, JsonException {
		log.info("Rest call [" + request.getRemoteHost() + request.getRequestURI() + " ]");
		log.debug("Rest call body [" + city + "]");

		CitiesPathResponse responseService = pathService.getPaths(city);
		ResponseEntity<String> response = new ResponseEntity<String>(responseService.toJson(), HttpStatus.OK);
		return response;

	}

}
