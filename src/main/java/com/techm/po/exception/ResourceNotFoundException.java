package com.techm.po.exception;

public class ResourceNotFoundException extends AbstractRuntimeException {

	private static final long serialVersionUID = 4863655198799337144L;
	
	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(Throwable exception) {
		super(exception);
	}

}
