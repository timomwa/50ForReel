package com.forreel.util.exception;

import com.forreel.exception.ForrealApplicationException;

public class SequenceException extends ForrealApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477675919289493161L;

	public SequenceException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public SequenceException(String message) {
		super(message);
	}

}
