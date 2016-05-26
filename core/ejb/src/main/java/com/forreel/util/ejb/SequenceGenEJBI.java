package com.forreel.util.ejb;

import com.forreel.util.exception.SequenceException;
import com.forreel.util.model.AppSequence;

public interface SequenceGenEJBI {
	
	public AppSequence getOrCreateNextSequence(String name) throws SequenceException;

}
