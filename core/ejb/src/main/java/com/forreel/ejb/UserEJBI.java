package com.forreel.ejb;

import java.util.List;

import com.forreel.model.CreditCardInformation;
import com.forreel.model.User;

public interface UserEJBI {
	
	
	public User saveOrUpdate(User user)  throws Exception;
	public CreditCardInformation saveOrUpdate(CreditCardInformation creditcard) throws Exception;
	public List<CreditCardInformation> listAllCreditCardInfo();
	public User findUserByUsername(String username);

}
