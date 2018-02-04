package com.demo.cities.domain.exceptions;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Class ApiError.
 */
public class ApiError {

	/** The http status. */
	@JsonProperty("api-status")
	private String httpStatus;

	/** The timestamp. */
	@JsonProperty("timestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	/** The message. */
	@JsonProperty("message")
	private String message;

	/** The debug message. */
	@JsonProperty("debugmessage")
	private String debugMessage;

	/** The sub errors. */
	private List<ApiSubError> subErrors;

	/**
	 * Instantiates a new api error.
	 */
	public ApiError() {
		timestamp = LocalDateTime.now();
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param status
	 *            the status
	 */
	public ApiError(String status) {
		this();
		this.httpStatus = status;
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param status
	 *            the status
	 * @param ex
	 *            the ex
	 */
	public ApiError(String status, Throwable ex) {
		this();
		this.httpStatus = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	/**
	 * Instantiates a new api error.
	 *
	 * @param status
	 *            the status
	 * @param message
	 *            the message
	 * @param ex
	 *            the ex
	 */
	public ApiError(String status, String message, Throwable ex) {
		this();
		this.httpStatus = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public String getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the http status.
	 *
	 * @param httpStatus
	 *            the new http status
	 */
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the debug message.
	 *
	 * @return the debug message
	 */
	public String getDebugMessage() {
		return debugMessage;
	}

	/**
	 * Sets the debug message.
	 *
	 * @param debugMessage
	 *            the new debug message
	 */
	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	/**
	 * To json.
	 *
	 * @return the string
	 * @throws JsonException
	 *             the json exception
	 */
	public String toJson() throws JsonException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, this);
			return sw.toString();
		} catch (IOException e) {
			throw new JsonException("Error marshalling " + this, e);
		}
	}
}