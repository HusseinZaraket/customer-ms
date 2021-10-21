package com.areeba.challenge.customer.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.areeba.challenge.customer.error.ErrorResponse;
import com.areeba.challenge.customer.exception.CustomerNotFoundException;
import com.areeba.challenge.customer.exception.InvalidMobileException;
import com.areeba.challenge.customer.exception.InvalidRequestException;

/**
 * Class that handle all exception thrown by application API, in order to return it in a common way with specific json format
 * 
 * @author Hussein Zaraket
 */
@ControllerAdvice
public class CustomControllerAdvice {

	/**
	 * Handle the InvalidMobileException and return json data the contain the needed details
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvalidMobileException.class)
	public ResponseEntity<ErrorResponse> handleInvalidMobileExceptions(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
				HttpStatus.OK);
	}

	/**
	 * Handle the CustomerNotFoundException and return json data the contain the needed details
	 * @param e
	 * @return
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()),
				HttpStatus.OK);
	}

	/**
	 * Handle the NullPointerException and return json data the contain the needed details
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResponse> handleNullPointerExceptions(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.OK);
	}
	
	/**
	 * Handle the InvalidRequestException and return json data the contain the needed details
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRequestExceptions(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.OK);
	}

	/**
	 * Handle the Exception and return json data the contain the needed details
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleExceptions(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
				HttpStatus.OK);
	}
}
