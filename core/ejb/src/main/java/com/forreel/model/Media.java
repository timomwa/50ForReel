package com.forreel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="media")
public class Media extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8903211412741582477L;
	
	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
