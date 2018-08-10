package test.net.douglashiura.leb.uid.scenario.glue.code;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.glue.code.GlueCodeOfScenarioSet;

public class TestGlueCodeOfScenarioSet {

	private String _package;
	private String _import;
	private String methods;
	private String body;
	private Interaction interaction;
	private Interaction interaction2;

	@Before
	public void setUp() {
		_package = "package test.com.net.douglashiura.example;\n";
		_import = "import net.douglashiura.us.Fixture;\n";
		methods = "";
		body = "@Fixture(\"EightPuzzle\")\npublic class FixtureEightPuzzle {" + methods + "\n}";
		interaction = new Interaction("", "EightPuzzle");
		interaction2 = new Interaction("", "EightPuzzle");
	}

	@Test
	public void anInteraction() throws Exception {
		String klasse = _package + _import + body;
		GlueCodeOfScenarioSet template = new GlueCodeOfScenarioSet("com.net.douglashiura.example",
				Arrays.asList(interaction, interaction2));
		assertEquals(1, template.getClasses().size());
		assertEquals(klasse, template.getClasses().get(0).toString());
	}

}
