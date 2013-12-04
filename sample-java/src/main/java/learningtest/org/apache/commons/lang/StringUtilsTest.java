package learningtest.org.apache.commons.lang;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void join() {
		String expected = "abc\tdef\tghi";

		List<String> strings = new ArrayList<String>();
		strings.add("abc");
		strings.add("def");
		strings.add("ghi");

		char separator = '\t';

		String joinedString = StringUtils.join(strings, separator);
		System.out.println(joinedString);
		assertThat(joinedString, is(expected));
	}

}
