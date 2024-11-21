package com.restapi.CRUDify.controller;

public class ErrorResponse {
	
	private String message;
	private int statusCode;
	private String error;

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
