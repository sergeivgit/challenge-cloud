package com.demo.cities.domain.exceptions;

/**
 * The Class ApiSubError.
 */
abstract class ApiSubError {

}

class ApiValidationError extends ApiSubError {
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}
}