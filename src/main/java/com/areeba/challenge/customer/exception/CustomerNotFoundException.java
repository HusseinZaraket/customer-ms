package com.areeba.challenge.customer.exception;

/**
 * CustomerNotFoundException throws when trying to retrieved a customer not exists
 * 
 * @author Hussein Zaraket
 */
public class CustomerNotFoundException extends Exception{

	private static final long serialVersionUID = 2328192047586954192L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(Long customerId, Throwable cause) {
		super("Customer not found with id: " + customerId, cause);
	}

	public CustomerNotFoundException(Long customerId) {
		super("Customer not found with id: " + customerId);
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
	}
}
