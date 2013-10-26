package learningtest.java.lang;

import org.junit.Test;

public class IntegerTest {

	@Test(expected = NumberFormatException.class)
	public void parseInt() {
		Integer.parseInt(" 1 ");
	}

	@Test(expected = NumberFormatException.class)
	public void valueOf() {
		Integer.valueOf(" 1 ");
	}

}
