package learningtest.java.lang;

import org.junit.Test;

public class SystemTest {

	@Test
	public void getProperty() {
		String lineSeparator = System.getProperty("line.separator");
		for (byte b : lineSeparator.getBytes()) {
			System.out.println(b);
		}
	}

	// NOTE:
	// System.lineSeparator() is available only on Java 7.
	@Test
	public void lineSeparator() {
		String lineSeparator = System.lineSeparator();
		for (byte b : lineSeparator.getBytes()) {
			System.out.println(b);
		}
	}

}
