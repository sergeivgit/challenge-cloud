package com.demo.citiesmanagement.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.demo.cities.domain.exceptions.ApiError;
import com.demo.cities.domain.exceptions.JsonException;
import com.demo.citiesmanagement.exceptions.CitiesManagementException;
import com.demo.citiesmanagement.exceptions.CitiesManagementException.Reason;

/**
 * The Class CityManagementRestExceptionHandler.
 */
@ControllerAdvice
public class CityManagementRestExceptionHandler {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CityManagementRestExceptionHandler.class);

	/**
	 * Handle cities management exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { CitiesManagementException.class })
	protected ResponseEntity<String> handleCitiesManagementException(CitiesManagementException ex, WebRequest request) {
		ApiError error = new ApiError();
		error.setHttpStatus(ex.getExceptionReason().toString());
		error.setMessage(ex.getMessage());
		error.setDebugMessage(ex.getMessage());

		ResponseEntity<String> response = null;
		try {
			if (ex.getExceptionReason() == Reason.CityNotFound || ex.getExceptionReason() == Reason.NoRoutes)
				response = new ResponseEntity<String>(error.toJson(), HttpStatus.NOT_FOUND); // 404 if not found the
																								// city or the routes
																								// for the city
			else
				response = new ResponseEntity<String>(error.toJson(), HttpStatus.BAD_REQUEST); // 400 if error
																								// validating data
																								// request
		} catch (JsonException e) {
			log.error("Error formatting error answer.", e);
			response = new ResponseEntity<String>("Error formatting error answer.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
																														// any
																														// error
		}
		return response;
	}

}
