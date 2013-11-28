package learningtest.akka.actor.example.geekcap.prime.message;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<Long> results = new TreeSet<Long>();

	public Set<Long> getResults() {
		return results;
	}

}
