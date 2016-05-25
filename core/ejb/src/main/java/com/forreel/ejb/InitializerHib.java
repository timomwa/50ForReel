package com.forreel.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

@Singleton
@Startup
public class InitializerHib {
	
	public static StandardPBEStringEncryptor myEncryptor;
	
	@PostConstruct
	public void init(){
		if(myEncryptor==null){
			System.out.println("\n\n\t  ******************  initializing..  ****************** \n\n");
			myEncryptor = new StandardPBEStringEncryptor();
			myEncryptor.setPassword("Admin123#@!");
			HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
			registry.registerPBEStringEncryptor("myHibernateStringEncryptor", myEncryptor);
		}
		
	}

}
