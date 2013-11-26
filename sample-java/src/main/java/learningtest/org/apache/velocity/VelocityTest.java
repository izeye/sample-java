package learningtest.org.apache.velocity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.BeforeClass;
import org.junit.Test;

public class VelocityTest {

	@BeforeClass
	public static void beforeClass() {
		Velocity.init();
	}

	@Test
	public void test() {
		VelocityContext context = new VelocityContext();
		context.put("name", "Velocity");
		Template template = Velocity
				.getTemplate("src/main/java/learningtest/org/apache/velocity/mytemplate.vm");
		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		assertThat(sw.toString(), is("Hello, Velocity!"));
	}

}
