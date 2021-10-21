package com.areeba.challenge.customer.exception;

/**
 * InvalidRequestException can be thrown any time an invalid request occur
 * 
 * @author Hussein Zaraket
 */
public class InvalidRequestException extends Exception{
	
	private static final long serialVersionUID = 2328192047586954192L;

	public InvalidRequestException() {
		super();
	}

	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRequestException(String message) {
		super(message);
	}

	public InvalidRequestException(Throwable cause) {
		super(cause);
	}
}
