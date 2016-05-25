package com.forreel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.forreel.constants.UserType;
import com.forreel.model.AbstractEntity;

/**
 * 
 * @author Timothy Mwangi Gikonyo
 * Created: 25 May 2016
 * timomwa@gmail.com
 * 
 * A subscriber. Subscribes to 
 * either a vendor or content.
 * 
 */

@Entity
@Table(name = "user")
public class User extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6597681151388711079L;
	
	@Column(name="username", unique=true, nullable=false)
	private String username;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private UserType type;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
	

}
