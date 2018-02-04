package com.demo.citiesmanagement.exceptions;


/**
 * It wraps error conditions on CitiesManagement service interface. There are
 * several causes implemented: NoCity: Text is missing: unable to check cities.
 * BadDepartureArrivalTime: The departure and/or arrival times are incorrect.
 * CityNotFound: Unable to look up citiy
 */
public class CitiesManagementException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 493057418722614623L;

	/**
	 * The Enum Causes.
	 */
	public static enum Reason {
		/** The No text. */
		NoText,
		/** The No routes. */
		NoRoutes,
		/** The Bad departure arrival time format. */
		BadDepartureArrivalTimeFormat,
		/** Bad times. */
		BadDepartureArrivalTime,
		/** City not found. */
		CityNotFound,
		/** City not stored in database. */
		DataError
	};

	/** The reason. */
	private Reason reason;

	/**
	 * Instantiates a new cities management service exception.
	 *
	 * @param errMsg
	 *            the err msg
	 */
	public CitiesManagementException(String errMsg) {
		super(errMsg);
	}

	/**
	 * Instantiates a new cities management service exception.
	 *
	 * @param errMsg
	 *            the err msg
	 * @param reason
	 *            the reason
	 */
	public CitiesManagementException(String errMsg, Reason reason) {
		super(errMsg);
		this.reason = reason;
	}

	/**
	 * Instantiates a new cities management service exception.
	 *
	 * @param errMsg
	 *            the err msg
	 * @param excp
	 *            the excp
	 */
	public CitiesManagementException(String errMsg, Throwable excp) {
		super(errMsg, excp);
	}

	/**
	 * Gets the exception cause.
	 *
	 * @return the exception cause
	 */
	public Reason getExceptionReason() {
		return this.reason;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CitiesManagementServiceException [reason=").append(reason).append("]");
		return builder.toString();
	}

}
