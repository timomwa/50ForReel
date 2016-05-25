package com.forreel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedStringType;

@Entity
@Table(name="credit_card_info")
@TypeDefs({
	@TypeDef(
	    name="encryptedString", 
	    typeClass=EncryptedStringType.class, 
	    parameters= {
	        @Parameter(
	        		name="encryptorRegisteredName", 
	        		value="myHibernateStringEncryptor"
	        		)
	    }
	)
})
public class CreditCardInformation extends AbstractEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1900065435574809362L;
	
	@Type(type="encryptedString")
	@Column(name="cardnumber")
	private String cardnumber;

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	
	

}
