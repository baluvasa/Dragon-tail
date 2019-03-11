package com.techm.po.exception;

public class DataAbstractRuntimeException extends AbstractRuntimeException {

	private static final long serialVersionUID = -4040321752700830494L;

	private Object data;
	private String errorCode;

	public DataAbstractRuntimeException() {
	}

	public DataAbstractRuntimeException(String s) {
		super(s);
	}

	public DataAbstractRuntimeException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public DataAbstractRuntimeException(Throwable throwable) {
		super(throwable);
	}

	public DataAbstractRuntimeException(String s, Throwable throwable, boolean b, boolean b1) {
		super(s, throwable, b, b1);
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
}
