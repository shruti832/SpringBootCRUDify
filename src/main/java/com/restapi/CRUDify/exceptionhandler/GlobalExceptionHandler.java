package com.restapi.CRUDify.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.restapi.CRUDify.controller.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

/**
 * GlobalExceptionHandler is a central place to handle exceptions in the
 * application.
 * 
 * This class provides custom exception handling for different types of
 * exceptions like validation errors, product not found exceptions, and other
 * runtime errors. It ensures that appropriate error responses are sent to the
 * client based on the exception type.
 * 
 * It uses Spring's {@link ControllerAdvice} to handle exceptions globally
 * across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Handles MethodArgumentNotValidException, which occurs when validation of
	 * method arguments fails.
	 * 
	 * This method extracts validation errors and creates a response with the field
	 * name and error message.
	 * 
	 * @param ex The exception containing the details of the validation errors.
	 * @return A {@link ResponseEntity} with a map of field names and error
	 *         messages, along with a BAD_REQUEST status.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles IllegalArgumentException, typically thrown when invalid arguments are
	 * passed to a method.
	 * 
	 * @param ex The exception that provides details of the invalid argument.
	 * @return A {@link ResponseEntity} with a message and a BAD_REQUEST status.
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles ResourceNotFoundException, which is thrown when a requested resource
	 * is not found.
	 * 
	 * @param ex The exception that contains the error message.
	 * @return A {@link ResponseEntity} containing an ErrorResponse with the message
	 *         and NOT_FOUND status.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), "Not Found");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles ProductNotFoundException, which is thrown when a product is not found
	 * by its ID.
	 * 
	 * @param ex The exception containing the error message specific to product not
	 *           found.
	 * @return A {@link ResponseEntity} containing an ErrorResponse with the message
	 *         and NOT_FOUND status.
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), 404, "Not Found");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles general exceptions that do not match the specific exception handlers
	 * above.
	 * 
	 * @param ex The exception containing the error message.
	 * @return A {@link ResponseEntity} with the message and an
	 *         INTERNAL_SERVER_ERROR status.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}