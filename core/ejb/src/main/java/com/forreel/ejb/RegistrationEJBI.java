package com.forreel.ejb;

import com.forreel.constants.UserType;
import com.forreel.exception.ForrealApplicationException;
import com.forreel.model.User;

public interface RegistrationEJBI {
	
	public User register(User user) throws ForrealApplicationException;
	public User register(String username, String password, UserType type) throws ForrealApplicationException;

}
