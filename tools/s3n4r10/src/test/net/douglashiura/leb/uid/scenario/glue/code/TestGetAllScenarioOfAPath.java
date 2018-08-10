package test.net.douglashiura.leb.uid.scenario.glue.code;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.glue.code.GetAllScenariosOfAPath;
import test.net.douglashiura.leb.uid.scenario.measures.uniform.TestUniformValues;

public class TestGetAllScenarioOfAPath {

	@Test
	public void aScenario() throws Exception {
		File file = new File(TestUniformValues.class.getResource("teste.us").toURI());
		File directory = new File(file.getAbsolutePath().replace("uniform/teste.us", ""));
		GetAllScenariosOfAPath reader = new GetAllScenariosOfAPath(directory);
		List<Interaction> scenarios = reader.getAll();
		assertEquals(1, scenarios.size());
	}

}
