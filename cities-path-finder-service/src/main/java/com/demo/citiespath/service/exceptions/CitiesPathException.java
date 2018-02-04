package com.demo.citiespath.service.exceptions;

/**
 * It wraps error conditions on CitiesManagement service interface. There are
 * several causes implemented: NoRoutes: There are no routes CityNotFound:
 * Unable to look up citiy
 */
public class CitiesPathException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 493057418722614623L;

	/**
	 * The Enum Causes.
	 */
	public static enum Reason {
		/** The No text. */
		NoRoutes,
		/** City not found. */
		CityNotFound
	};

	/** The reason. */
	private Reason reason;

	/**
	 * Instantiates a new cities management service exception.
	 *
	 * @param errMsg
	 *            the err msg
	 */
	public CitiesPathException(String errMsg) {
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
	public CitiesPathException(String errMsg, Reason reason) {
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
	public CitiesPathException(String errMsg, Throwable excp) {
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
		builder.append("CitiesPathException [reason=").append(reason).append("]");
		return builder.toString();
	}

}
