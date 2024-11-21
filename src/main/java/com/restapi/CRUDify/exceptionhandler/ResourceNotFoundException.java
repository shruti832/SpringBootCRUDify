package com.restapi.CRUDify.exceptionhandler;

/**
 * Custom exception class to represent a "resource not found" scenario. This
 * exception is thrown when a requested resource is not found in the system.
 * 
 * It extends {@link RuntimeException} to provide an unchecked exception that
 * can be used to indicate an error when a specific resource (e.g., product,
 * user, etc.) is not found.
 * 
 * This exception can be used in services and controllers to handle missing
 * resources gracefully.
 */

public class ResourceNotFoundException extends RuntimeException {
	
	/**
     * Serial version UID for the exception class.
     * This ensures proper serialization of the exception when it is thrown.
     */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ResourceNotFoundException with the specified detail message.
	 * 
	 * @param message The detail message, which is saved for later retrieval by the
	 * {@link Throwable#getMessage()} method.
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}
}