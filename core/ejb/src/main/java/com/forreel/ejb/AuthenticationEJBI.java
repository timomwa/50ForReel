package com.forreel.ejb;

import com.forreel.exception.ForrealApplicationException;
import com.forreel.model.User;

/**
 * 
 * @author Timothy Mwangi Gikonyo
 * Created: 25 May 2016
 * timomwa@gmail.com
 * 
 * Take care all of our encryption needs
 * from password hashing 
 * to hibernate encryption.
 * 
 */
public interface AuthenticationEJBI {
	
	/**
	 * Checks whether the two
	 * strings supplied match when 
	 * decrypted
	 * @param digest - java.lang.String - encrypted string
	 * @param plainText - java.lang.String - 
	 * plain text string to be encoded and matched against the encrypted string
	 * @return true if they match, else false.
	 */
	public boolean matchesTo(String digest, String plainText);
	
	
	/**
	 * Encrypts a plain text string
	 * @param plainText - java.lang.String - String to be encrypted
	 * 
	 * @return java.lang.String - digested string as pe config
	 */
	public String encrypt(String plainText);


	/**
	 * Tries to authenticate a user. 
	 * @throws com.forreel.exception.IncorrectUserCredentialsException.IncorrectUserCredentialsException
	 * if user does no exist.
	 * @param username - java.lang.String - username
	 * @param password - java.lang.String - password
	 * @return
	 */
	public User getUser(String username, String password) throws ForrealApplicationException;

}
