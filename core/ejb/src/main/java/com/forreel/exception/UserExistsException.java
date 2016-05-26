package com.forreel.exception;

public class UserExistsException extends ForrealApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -309493129194068326L;

	public UserExistsException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public UserExistsException(String message) {
		super(message);
	}

}
