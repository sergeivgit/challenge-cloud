package com.demo.citiespath.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import com.demo.cities.domain.exceptions.ApiError;
import com.demo.cities.domain.exceptions.JsonException;
import com.demo.citiespath.service.exceptions.CitiesPathException;
import com.demo.citiespath.service.exceptions.CitiesPathException.Reason;

/**
 * The Class CitiesPathRestExceptionHandler.
 * 		It handles the application exceptions providing a common way to handle those ones
 */
@ControllerAdvice
public class CitiesPathRestExceptionHandler {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(CitiesPathRestExceptionHandler.class);

	/**
	 * Handle cities path exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { CitiesPathException.class })
	protected ResponseEntity<String> handleCitiesPathException(CitiesPathException ex, WebRequest request) {
		ApiError error = new ApiError();
		error.setHttpStatus(ex.getExceptionReason().toString());
		error.setMessage(ex.getMessage());
		error.setDebugMessage(ex.getMessage());

		ResponseEntity<String> response = null;
		try {
			if (ex.getExceptionReason() == Reason.NoRoutes || ex.getExceptionReason() == Reason.CityNotFound)
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

	/**
	 * Handle service communication exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { HttpClientErrorException.class })
	protected ResponseEntity<String> handleServiceCommunicationException(HttpClientErrorException ex,
			WebRequest request) {
		ApiError error = new ApiError();
		error.setHttpStatus("ErrorGettingCities");
		error.setMessage("No Routes from cities management service.");
		error.setDebugMessage(ex.getMessage());

		ResponseEntity<String> response = null;
		try {
			response = new ResponseEntity<String>(error.toJson(), HttpStatus.NOT_FOUND); // 400 if error validating data
																							// request
		} catch (JsonException e) {
			log.error("Error formatting error answer.", e);
			response = new ResponseEntity<String>("Error formatting error answer.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
																														// any
																														// error
		}
		return response;
	}

	/**
	 * Handle cities path exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(value = { JsonException.class })
	protected ResponseEntity<String> handleCitiesPathException(JsonException ex, WebRequest request) {
		ApiError error = new ApiError();
		error.setHttpStatus("ErrorFormatingAnswer");
		error.setMessage(ex.getMessage());
		error.setDebugMessage(ex.getMessage());

		ResponseEntity<String> response = null;
		try {
			response = new ResponseEntity<String>(error.toJson(), HttpStatus.INTERNAL_SERVER_ERROR); // 400 if error
																										// validating
																										// data request
		} catch (JsonException e) {
			log.error("Error formatting error answer.", e);
			response = new ResponseEntity<String>("Error formatting error answer.", HttpStatus.INTERNAL_SERVER_ERROR); // 500
																														// any
																														// error
		}
		return response;
	}

}
