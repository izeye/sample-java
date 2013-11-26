package learningtest.initialization;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InitializationTest {

	@Test
	public void test() {
		ClassA classA = new ClassA();

		assertThat(classA.a, is(false));
		assertThat(classA.b, is(true));
	}

	static class ClassA {
		boolean a, b = true;
	}

}
