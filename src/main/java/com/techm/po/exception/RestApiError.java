package com.techm.po.exception;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RestApiError implements Serializable {

	private static final long serialVersionUID = -3485705798225551794L;

	private HttpStatus httpStatus;
	private String message;
	private String exceptionMessage;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	
	private RestApiError() {
		this.timeStamp = LocalDateTime.now();
	}

    RestApiError(HttpStatus httpStatus, Throwable exception) {
		this();
		this.httpStatus = httpStatus;
		this.message = "Unknown Error";
        this.exceptionMessage = exception != null ? exception.getLocalizedMessage() : null;
    }

    RestApiError(HttpStatus httpStatus, String message, Throwable exception) {
		this();
		this.httpStatus = httpStatus;
		this.message = message;
        this.exceptionMessage = exception != null ? exception.getLocalizedMessage() : null;
    }

    RestApiError(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
		this.message = "Unknown Error";
	}

    /**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * @param exceptionMessage the exceptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * @return the timeStamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
