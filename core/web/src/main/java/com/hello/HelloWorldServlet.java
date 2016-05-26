package com.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.forreel.constants.UserType;
import com.forreel.ejb.AuthenticationEJBI;
import com.forreel.ejb.RegistrationEJBI;
import com.forreel.ejb.UserEJBI;
import com.forreel.exception.ForrealApplicationException;
import com.forreel.exception.IncorrectUserCredentialsException;
import com.forreel.exception.UserExistsException;
import com.forreel.model.CreditCardInformation;
import com.forreel.model.User;

@WebServlet(urlPatterns={"/helloworld", "/index.html"})
public class HelloWorldServlet extends HttpServlet{
	
	@EJB
	private UserEJBI userEJB;
	
	@EJB
	private RegistrationEJBI registrationEJB;
	
	@EJB
	private AuthenticationEJBI authenticationEJB;
	
	private Logger logger = Logger.getLogger(getClass());
	
	private static int counter = 1;

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
		
		final String ACTION = req.getParameter("ACTION");
		
		
		
		
		if(ACTION==null || ACTION.equalsIgnoreCase("testencryption")){
			
			testEncryption(req,resp);
			
		}else if (ACTION.equalsIgnoreCase("userregistration") || ACTION.equalsIgnoreCase("createaccount")){
			
			testUserReg(req,resp);
			
		}else if (ACTION.equalsIgnoreCase("testlogin")){
			
			testlogin(req,resp);
			
		}
		
		 
	}

	private void testlogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		PrintWriter out = resp.getWriter();
		
		try {
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			User user = authenticationEJB.getUser(username,password);
			out.println(" > successfully logged in! <br/> User: <br/> "+user);
			
		}catch(IncorrectUserCredentialsException e){
			out.println("Wrong username / password. "+e.getMessage());
		} catch(UserExistsException e){
			out.println("User probably exists. "+e.getMessage());
		}catch (ForrealApplicationException e) {
			logger.error(e.getMessage(), e);
			out.println("Problem occurred. "+e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			out.println("Problem occurred. "+e.getMessage());
		}finally{
			if(out!=null)
				out.close();
		}
		
	}

	private void testUserReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		
		try {
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			User user = registrationEJB.register(username, password, UserType.SUBSCRIBER);
			
			out.println(" > successfully created new user. "+user);
			
		} catch(UserExistsException e){
			out.println("User probably already exists. "+e.getMessage());
		}catch (ForrealApplicationException e) {
			out.println("Problem occurred. "+e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			out.println("Problem occurred. "+e.getMessage());
		}finally{
			if(out!=null)
				out.close();
		}
		
	}

	private void testEncryption(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		CreditCardInformation creditcard = new CreditCardInformation();
		creditcard.setCardnumber("05282372258"+counter);
		counter++;
		 try {
			 resp.setContentType("text/html");
			 userEJB.saveOrUpdate(creditcard);
			 out.println("<b>New credit card entry created!</b>");
			List<CreditCardInformation> creditcardinfo = userEJB.listAllCreditCardInfo();
			StringBuffer sb = new StringBuffer();
			sb.append("<br/>");
			int i = 0;
			for(CreditCardInformation credinfo : creditcardinfo){
				sb.append(i+". id ->").append(credinfo.getId()).append(" CardNum: ").append(credinfo.getCardnumber()).append("<br/>");
				i++;
			}
			out.println(sb.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			out.println("There was a problem creating user! "+e.getMessage());
		}finally{
			if(out!=null)
				out.close();
		}
		
	}
	

}
