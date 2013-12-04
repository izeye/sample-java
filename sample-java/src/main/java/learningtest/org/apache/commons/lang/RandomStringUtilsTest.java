package learningtest.org.apache.commons.lang;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

public class RandomStringUtilsTest {

	@Test
	public void random() {
		int length = 10;
		String randomString = RandomStringUtils.random(length, true, true);
		System.out.println(randomString);
		assertThat(randomString.length(), is(length));
	}

}
