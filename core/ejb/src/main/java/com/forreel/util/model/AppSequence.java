package com.forreel.util.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import com.forreel.model.AbstractEntity;

/**
 * System-wide sequence
 * 
 * @author Timothy Mwangi Gikonyo
 * Created: 25 May 2016
 * timomwa@gmail.com
 * 
 */
@Entity
@Table(name = "tbl_sequence", indexes =  {@Index(columnList = "timeStamp", name="apseqtsitx"),
		@Index(columnList = "name", name="apseqnmidx")} )
public class AppSequence extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7289509854040668165L;
	
	@Column(name = "name",nullable=false,unique=true)
	private String name;
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "nextval")
	private BigInteger nextval;
	
	@Column(name = "suffix")
	private String suffix;
	
	@Column(name = "timeStamp",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return ( (prefix==null) ? "" : prefix);
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public BigInteger getNextval() {
		return nextval;
	}

	public void setNextval(BigInteger nextval) {
		this.nextval = nextval;
	}

	public String getSuffix() {
		return ( (suffix==null)?"":suffix);
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getSeqNumber(){
		return getPrefix()+getNextval()+getSuffix();
	}
	
	@PrePersist
	@PreUpdate
	public void onCreate(){
		if(timeStamp==null)
			timeStamp = new Date();
	}

}
