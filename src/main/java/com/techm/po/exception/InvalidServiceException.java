package com.techm.po.exception;

public class InvalidServiceException extends DataAbstractRuntimeException {

	private static final long serialVersionUID = 7059108814336360596L;
	
	 public InvalidServiceException() {
	    }

	    public InvalidServiceException(String s) {
	        super(s);
	    }

	    public InvalidServiceException(String s, Throwable throwable) {
	        super(s, throwable);
	    }

	    public InvalidServiceException(Throwable throwable) {
	        super(throwable);
	    }

	    public InvalidServiceException(String s, Throwable throwable, boolean b, boolean b1) {
	        super(s, throwable, b, b1);
	    }

}
