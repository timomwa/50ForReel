package com.forreel.ejb;
import java.util.Date;

import javax.ejb.Stateless;

@Stateless
public class GreetingEJBImpl implements GreetingEJBI {

	public Date getTime(){
		return new Date();
	}
}
