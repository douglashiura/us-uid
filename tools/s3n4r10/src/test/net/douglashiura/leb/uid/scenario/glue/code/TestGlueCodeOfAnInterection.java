package test.net.douglashiura.leb.uid.scenario.glue.code;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import net.douglashiura.leb.uid.scenario.glue.code.ClassTemplate;
import net.douglashiura.us.Input;
import net.douglashiura.us.Interaction;
import net.douglashiura.us.Output;
import net.douglashiura.us.Transaction;

public class TestGlueCodeOfAnInterection {

	private String _package;
	private String _import;
	private String methods;
	private String body;
	private Interaction interaction;
	private Input input;
	private Output output;
	private Interaction interaction2;

	@Before
	public void setUp() {
		_package = "package test.com.net.douglashiura.example;\n";
		_import = "import net.douglashiura.us.Fixture;\n";
		methods = "";
		body = "@Fixture(\"EightPuzzle\")\npublic class FixtureEightPuzzle {" + methods + "\n}";
		interaction = new Interaction("", "EightPuzzle");
		interaction2 = new Interaction("", "EightPuzzle");
		input = new Input("", "home1", "");
		output = new Output("", "home1", "");
	}

	@Test
	public void anInteraction() throws Exception {
		String klasse = _package + _import + body;
		ClassTemplate template = new ClassTemplate("com.net.douglashiura.example", Arrays.asList(interaction));
		assertEquals(klasse, template.toString());
	}

	@Test
	public void input() throws Exception {
		String inputMethod = "	public void setHome1(String value){\n	}";
		String klasse = _package + _import + body.replace("}", inputMethod + "\n}");
		interaction.addInput(input);
		ClassTemplate template = new ClassTemplate("com.net.douglashiura.example", Arrays.asList(interaction));
		assertEquals(klasse, template.toString());
	}

	@Test
	public void inputInTwoMethods() throws Exception {
		String inputMethod = "	public void setHome1(String value){\n	}";
		String inputMethod2 = "\n	public void setHome2(String value){\n	}";
		String klasse = _package + _import + body.replace("}", inputMethod+inputMethod2 + "\n}");
		interaction.addInput(input);
		interaction2.addInput(new Input("", "home2", ""));
		ClassTemplate template = new ClassTemplate("com.net.douglashiura.example", Arrays.asList(interaction,interaction2));
		assertEquals(klasse, template.toString());
	}
	
	@Test
	public void transition() throws Exception {
		String inputMethod = "	public void toAnother(){\n	}";
		String klasse = _package + _import + body.replace("}", inputMethod + "\n}");
		Interaction aTarget = new Interaction("", "Another");
		Transaction transaction = new Transaction("", interaction, aTarget);
		interaction.setTransaction(transaction);
		ClassTemplate template = new ClassTemplate("com.net.douglashiura.example", Arrays.asList(interaction));
		assertEquals(klasse, template.toString());
	}

	@Test
	public void output() throws Exception {
		String inputMethod = "	public String getHome1(){\n		return null;\n	}";
		String klasse = _package + _import + body.replace("}", inputMethod + "\n}");
		interaction.addOutput(output);
		ClassTemplate template = new ClassTemplate("com.net.douglashiura.example", Arrays.asList(interaction));
		assertEquals(klasse, template.toString());
	}

}
