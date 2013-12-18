package learningtest.javax.script.js;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class RhinoEngineTest {

	@Test
	public void eval() {
		String name = "Johnny";

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		try {
			engine.put("name", name);
			engine.eval("print('Hello ' + name + '!')");
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		System.out.println(engine.getFactory().getEngineName());
	}

}
