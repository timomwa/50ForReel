package com.forreel.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.foreel.dao.CreditCardInformationDAOI;
import com.foreel.dao.UserDAOI;
import com.forreel.model.CreditCardInformation;
import com.forreel.model.User;

@Stateless
public class UserEJBImpl implements UserEJBI {
	
	
	@Inject
	private UserDAOI userDAO;
	
	@Inject
	private CreditCardInformationDAOI creditCardDAO;
	
	
	public User saveOrUpdate(User user) throws Exception{
		return userDAO.save(user);
	}
	
	public CreditCardInformation saveOrUpdate(CreditCardInformation creditcard) throws Exception{
		return creditCardDAO.save(creditcard);
	}

}
