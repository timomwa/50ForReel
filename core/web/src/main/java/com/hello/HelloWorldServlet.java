package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.hibernate4.type.EncryptedStringType;

import com.forreel.constants.UserType;
import com.forreel.ejb.UserEJBI;
import com.forreel.model.CreditCardInformation;
import com.forreel.model.User;

@WebServlet(urlPatterns={"/helloworld", "/index.html"})
public class HelloWorldServlet extends HttpServlet{
	
	@EJB
	private UserEJBI userEJB;
	
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 7024649224642762262L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		CreditCardInformation creditcard = new CreditCardInformation();
		creditcard.setCardnumber("05282372258");
		 try {
			out.println("New user created. ID: "+userEJB.saveOrUpdate(creditcard));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			out.println("There was a problem creating user! "+e.getMessage());
		}finally{
			out.close();
		}
		 
	}
	

}
