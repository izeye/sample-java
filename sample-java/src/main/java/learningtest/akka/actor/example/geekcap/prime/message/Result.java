package learningtest.akka.actor.example.geekcap.prime.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Long> results = new ArrayList<Long>();

	public List<Long> getResults() {
		return results;
	}

}
