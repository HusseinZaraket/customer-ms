package com.areeba.challenge.customer.error;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Common error response class that is used to return the exception with it's needed details when occur
 *  
 * @author Hussein Zaraket
 */
public class ErrorResponse {

	// the time when the issue occur
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	// the response code of the error
	private int code;
	// status of the error
	private String status;
	// message to be retrieved to the client
	private String message;
	// data object
	private Object data;

	public ErrorResponse() {
		timestamp = new Date();
	}

	public ErrorResponse(HttpStatus httpStatus, String message) {
		this();
		this.code = httpStatus.value();
		this.status = httpStatus.name();
		this.message = message;
	}

	public ErrorResponse(HttpStatus httpStatus, String message, Object data) {
		this(httpStatus, message);
		this.data = data;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
