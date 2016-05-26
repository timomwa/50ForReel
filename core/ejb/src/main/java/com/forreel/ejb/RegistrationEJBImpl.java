package com.forreel.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.forreel.constants.UserType;
import com.forreel.exception.ForrealApplicationException;
import com.forreel.exception.UserExistsException;
import com.forreel.exception.UserRegistrationException;
import com.forreel.model.User;
import com.forreel.util.ejb.SequenceGenEJBI;

@Stateless
public class RegistrationEJBImpl implements RegistrationEJBI {
	
	private static final String USER_ACC_SEQ_NAME = "UAC"; 

	@EJB
	private UserEJBI userEJB;
	
	@EJB
	private SequenceGenEJBI sequenceEJB;
	
	@EJB
	private AuthenticationEJBI authenticatorEJB;
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public User register(User user) throws ForrealApplicationException{
		if(user==null)
			throw new UserRegistrationException("User to register is null!");
		if(user.getUsername()==null)
			throw new UserRegistrationException("User's username to register is null!");
		try {
			return userEJB.saveOrUpdate(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UserRegistrationException("problem occurred while persisting user object!");
		}
	}
	
	@Override
	public User register(String username, String password, UserType type) throws ForrealApplicationException{
		
		if(username==null)
			throw new UserRegistrationException("username passed is null!");
		if(password==null)
			throw new UserRegistrationException("password passed is null!");
		User user = userEJB.findUserByUsername(username);
		if(user!=null)
			throw new UserExistsException("User with the username \""+username+"\" already exists!");
		
		user = new User();
		user.setUsername(username);
		user.setPwdHash(authenticatorEJB.encrypt(String.format("%s%s", password, username)));
		user.setAccountCode( sequenceEJB.getOrCreateNextSequence(USER_ACC_SEQ_NAME).getSeqNumber() );
		user.setType(Long.valueOf(type.ordinal()));
		
		try {
			return userEJB.saveOrUpdate(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UserRegistrationException("problem occurred while persisting user object!");
		}
	}

}
