package com.techm.po.exception;

import org.springframework.http.HttpStatus;

public class RestApiErrorData extends RestApiError {

	private static final long serialVersionUID = -5772697809785702394L;

	private Object data;
	private String errorCode;

	/**
	 * @param httpStatus
	 * @param exception
	 */
	public RestApiErrorData(HttpStatus httpStatus, Throwable exception) {
		super(httpStatus, exception);
	}

	/**
	 * @param httpStatus
	 * @param message
	 * @param exception
	 */
	public RestApiErrorData(HttpStatus httpStatus, String message, Throwable exception) {
		super(httpStatus, message, exception);
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void mapExceptionData(DataAbstractRuntimeException incompleteException) {

        if (null != incompleteException) {
            this.data = incompleteException.getData();
            this.errorCode = incompleteException.getErrorCode();
        }
	}

	
}
