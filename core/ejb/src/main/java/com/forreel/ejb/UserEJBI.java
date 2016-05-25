package com.forreel.ejb;

import com.forreel.model.CreditCardInformation;
import com.forreel.model.User;

public interface UserEJBI {
	
	
	public User saveOrUpdate(User user)  throws Exception;
	public CreditCardInformation saveOrUpdate(CreditCardInformation creditcard) throws Exception;

}
