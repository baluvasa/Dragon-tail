package com.techm.po.exception;

public class AbstractRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1178745914514356040L;

	private String[] params;

    public AbstractRuntimeException() {
    }

    public AbstractRuntimeException(String s) {
        super(s);
    }

    public AbstractRuntimeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AbstractRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public AbstractRuntimeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
