package com.forreel.exception;

public class UserRegistrationException extends ForrealApplicationException {

	private static final long serialVersionUID = -4589001336271022090L;

	public UserRegistrationException(String message) {
		super(message);
	}
	public UserRegistrationException(String message, Throwable throwable){
		super(message,throwable);
	}
	
	

}
