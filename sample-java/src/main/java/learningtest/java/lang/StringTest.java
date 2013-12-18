package learningtest.java.lang;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringTest {

	@Test
	public void matches() {
		String string = "abc";
		assertThat(string.matches("abc"), is(true));
		assertThat(string.matches("abd"), is(false));
		assertThat(string.matches("ab.*"), is(true));
	}

	@Test
	public void concateNull() {
		String nullString = null;
		System.out.println("test" + nullString);
	}

}
