package com.areeba.challenge.customer.exception;

/**
 * InvalidMobileException throws when an invalid mobileNumber recieved
 * 
 * @author Hussein Zaraket
 */
public class InvalidMobileException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidMobileException() {
		super();
	}

	public InvalidMobileException(String mobileNumber, Throwable cause) {
		super("Invalid mobile number: " + mobileNumber, cause);
	}

	public InvalidMobileException(String mobileNumber) {
		super("Invalid mobile number: " + mobileNumber);
	}

	public InvalidMobileException(Throwable cause) {
		super(cause);
	}
}
