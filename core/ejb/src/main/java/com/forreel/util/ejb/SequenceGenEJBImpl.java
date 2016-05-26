package com.forreel.util.ejb;


import java.math.BigInteger;

import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.forreel.constants.AppPropertyHolder;
import com.forreel.util.exception.SequenceException;
import com.forreel.util.model.AppSequence;



@Singleton
@Remote
public class SequenceGenEJBImpl implements SequenceGenEJBI {

	private Logger logger = Logger.getLogger(getClass());

	@PersistenceContext(unitName = AppPropertyHolder.PERSISTENCE_UNIT_NAME)
	private EntityManager em;
		
	private AppSequence getSequenceCreateIfNotExists(String name) throws SequenceException{
		AppSequence cmp_sequence = findSequenceByName(name);
		
		if(cmp_sequence!=null)
			return cmp_sequence;
		
		try {
			cmp_sequence = new AppSequence();
			cmp_sequence.setName(name);
			cmp_sequence.setNextval(BigInteger.ZERO);
			cmp_sequence.setPrefix(name);
			cmp_sequence.setSuffix(String.valueOf(name.hashCode()));
			cmp_sequence = em.merge(cmp_sequence);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		return cmp_sequence;
		
		
	}
	
	
	@Lock(LockType.READ)
	@AccessTimeout(unit=java.util.concurrent.TimeUnit.SECONDS, value = 15)
	private AppSequence findSequenceByName(String name){
		AppSequence sequence = null;
		try{
			Query query = em.createQuery("from AppSequence s WHERE s.name=:name");
			query.setParameter("name", name);
			sequence = (AppSequence) query.getSingleResult();
		}catch(javax.persistence.NoResultException nre){
			logger.warn("Sequence with the name "+name+" not found. We should create a new one");
		}catch(Exception exp){
			logger.error(exp.getMessage(),exp);
		}
		return sequence;
	}
	
	
	@Lock(LockType.WRITE)
	@AccessTimeout(unit=java.util.concurrent.TimeUnit.SECONDS, value = 15)
	@Override
	public AppSequence getOrCreateNextSequence(String name) throws SequenceException{
		
		try{
			AppSequence sequence = getSequenceCreateIfNotExists(name);
			BigInteger nextVal = sequence.getNextval().add(BigInteger.ONE);
			Query qry = em.createQuery("update AppSequence cs SET cs.nextval=:nextval_ where cs.name=:name_");
			qry.setParameter("nextval_", nextVal);
			qry.setParameter("name_", name);
			qry.executeUpdate();
			return sequence;
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
			
			throw new SequenceException("Could not get new sequence",e);
		}
		
	}
}
