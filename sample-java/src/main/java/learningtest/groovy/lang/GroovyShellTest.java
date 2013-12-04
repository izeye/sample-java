package learningtest.groovy.lang;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.junit.Test;

public class GroovyShellTest {

	@Test
	public void evaluate() {
		Binding binding = new Binding();
		binding.setVariable("foo", new Integer(2));
		GroovyShell shell = new GroovyShell(binding);

		Object value = shell
				.evaluate("println 'Hello World!'; x = 123; return foo * 10");
		assertThat((Integer) value, is(new Integer(20)));
		assertThat((Integer) binding.getVariable("x"), is(new Integer(123)));
	}

}
