package com.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forreel.ejb.GreetingEJBI;

@WebServlet(urlPatterns={"/helloworld", "/index.html"})
public class HelloWorldServlet extends HttpServlet{
	
	@EJB
	private GreetingEJBI greetingEJB;

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
		 out.println("Hello World The time is : "+greetingEJB.getTime());
		 out.close();
	}
	

}
