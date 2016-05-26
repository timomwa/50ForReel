package com.forreel.exception;

public class ForrealApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5714559354767887649L;
	private Throwable throwable;
	private String message;
	
	public ForrealApplicationException(String message){
		this.message = message;
	}
	public ForrealApplicationException(String message, Throwable throwable){
		this.message = message;
		this.throwable = throwable;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public String getMessage() {
		return message;
	}

}
