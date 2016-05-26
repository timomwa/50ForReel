package com.forreel.exception;

public class IncorrectUserCredentialsException extends ForrealApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7315414494961367844L;

	public IncorrectUserCredentialsException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public IncorrectUserCredentialsException(String message) {
		super(message);
	}

}
