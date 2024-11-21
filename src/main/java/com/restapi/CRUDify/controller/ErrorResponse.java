package com.restapi.CRUDify.controller;

/**
 * This class represents the structure of an error response sent back to the
 * client when an exception or error occurs during API processing. The response
 * includes a message, the HTTP status code, and a description of the error.
 */
public class ErrorResponse {
	
	private String message;
	private int statusCode;
	private String error;

	/**
	 * Constructs a new ErrorResponse with the specified message, status code, and
	 * error.
	 * 
	 * @param message    The error message describing the issue.
	 * @param statusCode The HTTP status code to be returned.
	 * @param error      A string representing the error type or category.
	 */
	public ErrorResponse(String message, int statusCode, String error) {
		this.message = message;
		this.statusCode = statusCode;
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
