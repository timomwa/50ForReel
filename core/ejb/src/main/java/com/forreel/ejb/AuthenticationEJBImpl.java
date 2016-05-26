package com.forreel.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.forreel.exception.ForrealApplicationException;
import com.forreel.exception.IncorrectUserCredentialsException;
import com.forreel.model.User;
import com.forreel.util.ejb.EncryptorEJB;


@Stateless
public class AuthenticationEJBImpl implements AuthenticationEJBI {
	
	@EJB
	private UserEJBI userEJB;
	
	
	/* (non-Javadoc)
	 * @see com.forreel.ejb.AuthenticationEJBI#matchesTo(java.lang.String, java.lang.String)
	 */
	public boolean matchesTo(String digest, String plainText){
		if(digest==null)
			return false;
		if(plainText==null)
			return false;
		return EncryptorEJB.password_digestor.matches(plainText, digest);
	}


	/* (non-Javadoc)
	 * @see com.forreel.ejb.AuthenticationEJBI#encrypt(java.lang.String)
	 */
	@Override
	public String encrypt(String plainText) {
		return EncryptorEJB.password_digestor.digest(plainText);
	}
	
	@Override
	public User getUser(String username, String password) throws ForrealApplicationException{
		User user = userEJB.findUserByUsername(username);
		if(user==null)
			throw new IncorrectUserCredentialsException("Incorrect credentials!");
		if(user.getPwdHash()==null)
			throw new ForrealApplicationException("Could not verify account credentials.");
		if(EncryptorEJB.password_digestor.matches( String.format("%s%s", password,username),user.getPwdHash() ))
			return user;
		else
			throw new IncorrectUserCredentialsException("Incorrect credentials!");
	}

}
