package com.forreel.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.forreel.dao.CreditCardInformationDAOI;
import com.forreel.dao.UserDAOI;
import com.forreel.model.CreditCardInformation;
import com.forreel.model.User;

@Stateless
public class UserEJBImpl implements UserEJBI { 
	
	
	@Inject
	private UserDAOI userDAO;
	
	@Inject
	private CreditCardInformationDAOI creditCardDAO;
	
	@Override
	public User saveOrUpdate(User user) throws Exception{
		return userDAO.save(user);
	}
	
	@Override
	public CreditCardInformation saveOrUpdate(CreditCardInformation creditcard) throws Exception{
		return creditCardDAO.save(creditcard);
	}
	
	@Override
	public List<CreditCardInformation> listAllCreditCardInfo(){
		return creditCardDAO.list(0, 10000);
	}
	
	public User findUserByUsername(String username){
		return userDAO.findBy("username", username);
	}

}
