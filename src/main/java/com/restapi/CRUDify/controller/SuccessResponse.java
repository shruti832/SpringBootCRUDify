package com.restapi.CRUDify.controller;

public class SuccessResponse {
	private String message;
	private int statusCode;

	/**
	 * Represents a success response that is returned after performing an operation
	 * successfully.
	 * 
	 * This class is used to send a message and status code back to the client to
	 * indicate that the operation was successful.
	 */
	public SuccessResponse(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
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
	
}