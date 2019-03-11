package com.techm.po.exception;

import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final MessageSource messageSource;
	
	@Autowired
	public GlobalExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGenericException(Exception ex) {
		RestApiError error = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		return buildResponseEntity(error);
	}
	
	@ExceptionHandler(InvalidServiceException.class)
	protected ResponseEntity<Object> handleInvalidServiceException(InvalidServiceException ex, Locale locale) {
		RestApiErrorData error = new RestApiErrorData(HttpStatus.UNPROCESSABLE_ENTITY, ex);

		try {
			if (null != ex.getMessage()) {
				error.setMessage(messageSource.getMessage(ex.getMessage(), ex.getParams(), locale));
			}
			error.mapExceptionData(ex);
			return buildResponseEntity(error);
		} catch (Exception e) {
			return buildApiErrorObject(e);
		}
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
		RestApiError error = new RestApiError(HttpStatus.NOT_FOUND, ex);
		return buildResponseEntity(error);
	}
	
	private ResponseEntity<Object> buildApiErrorObject(Throwable ex) {
		//LOG.error("Unable to handle exception in GlobalExceptionHandler ", ex.getMessage());
		RestApiError error = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
		return buildResponseEntity(error);
	}
	
	private ResponseEntity<Object> buildResponseEntity(RestApiError restApiError) {
		return new ResponseEntity<>(restApiError, restApiError.getHttpStatus());
	}
}
