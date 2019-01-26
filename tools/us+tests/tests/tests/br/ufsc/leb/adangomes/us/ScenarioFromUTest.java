package tests.br.ufsc.leb.adangomes.us;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import net.douglashiura.us.ScenarioFromText;
import net.douglashiura.us.run.UInput;
import net.douglashiura.us.run.UInteraction;
import net.douglashiura.us.run.UOutput;

public class ScenarioFromUTest {

	private String initialInteraction = "{\"type\": \"br.ufsc.leb.uid.scenario.Interacao\","
			+ "\"id\": \"8c9987b0-3189-1087-2aba-9df20814018b\"," + "\"x\": 638," + "\"y\": 291," + "\"width\": 93,"
			+ "\"height\": 80," + "\"alpha\": 1," + "\"userData\": {" + "\"result\": \"\","
			+ "\"model\": \"Fixture8Puzzle\"" + "}," + "\"cssClass\": \"br_ufsc_leb_uid_scenario_Interacao\","
			+ "\"ports\": [" + "{" + "\"name\": \"output0\"," + "\"port\": \"draw2d.OutputPort\","
			+ "\"locator\": \"br.ufsc.leb.uid.scenario.ResourceLocator\"" + "}" + "]," + "\"bgColor\": \"#FFFFFF\","
			+ "\"color\": \"#000000\"," + "\"stroke\": 1," + "\"radius\": 500" + "}";

	private String targetInteraction = "{\"type\": \"br.ufsc.leb.uid.scenario.Interacao\",\"id\": \"6063b665-b963-62dd-de91-195d0d6791ad\","
			+ "\"x\": 626,\"y\": 156,\"width\": 113,\"height\": 83,\"alpha\": 1,\"userData\": {\"result\":\"\",\"model\": \"Alvo\"},"
			+ "\"cssClass\": \"br_ufsc_leb_uid_scenario_Interacao\",\"ports\": [{\"name\": \"input0\","
			+ "\"port\": \"draw2d.InputPort\",\"locator\": \"br.ufsc.leb.uid.scenario.TargetLocator\"}],\"bgColor\": \"#FFFFFF\","
			+ "\"color\": \"#000000\",\"stroke\": 1,\"radius\": 500 }";

	private String transaction = "{\"type\": \"br.ufsc.leb.uid.scenario.Transaction\","
			+ "\"id\": \"dcc6fc42-5fb4-d204-0d39-fbaf82c1cf85\"," + "\"alpha\": 1,\"userData\": {" + "\"result\": \"\","
			+ "\"model\": \"\" }," + "\"cssClass\": \"br_ufsc_leb_uid_scenario_Transaction\","
			+ "\"stroke\": 1,\"color\": \"#000000\",\"outlineStroke\": 0," + "\"outlineColor\": \"none\","
			+ "\"policy\": \"draw2d.policy.line.LineSelectionFeedbackPolicy\","
			+ "\"router\": \"draw2d.layout.connection.DirectRouter\"," + "\"radius\": 2,\"source\": {"
			+ "\"node\":\"8c9987b0-3189-1087-2aba-9df20814018b\"," + "\"port\": \"output0\" },\"target\": {"
			+ "\"node\": \"6063b665-b963-62dd-de91-195d0d6791ad\",\"port\":\"input0\"" + "}}";

	private String input = "{\"type\": \"br.ufsc.leb.uid.scenario.UserInput\",\"id\": \"deb51e97-bd3f-82c3-74d2-ef1815a42265\","
			+ "\"x\": 654,\"y\": 205,\"width\": 43,\"height\": 23,\"alpha\": 1,\"userData\": {\"result\": \"\",\"model\": \"entrada\""
			+ "},\"cssClass\": \"br_ufsc_leb_uid_scenario_UserInput\",\"composite\": \"6063b665-b963-62dd-de91-195d0d6791ad\","
			+ "\"ports\": [],\"bgColor\": \"none\",\"color\": \"#000000\",\"stroke\": 1,\"radius\": 0,"
			+ "\"text\": \"Valor\", \"outlineStroke\": 0,\"outlineColor\": \"none\",\"fontSize\": 12,"
			+ "\"fontColor\": \"#080808\",\"fontFamily\": null }";

	private String output = "{\"type\": \"br.ufsc.leb.uid.scenario.SystemOutput\",\"id\": \"a2df2c45-be51-27cc-ae8d-f2772ceaa6f4\","
			+ "\"x\": 647,\"y\": 170,\"width\": 43,\"height\": 23, \"alpha\": 1,\"userData\": {\"result\": \"\","
			+ "\"model\": \"saida\"},\"cssClass\": \"br_ufsc_leb_uid_scenario_SystemOutput\",\"composite\": \"6063b665-b963-62dd-de91-195d0d6791ad\","
			+ "\"ports\": [],\"bgColor\": \"none\",\"color\": \"#FFFFFF\",\"stroke\": 1,\"radius\": 0,"
			+ "\"text\": \"Valor\",\"outlineStroke\": 0,\"outlineColor\": \"none\",\"fontSize\": 12,\"fontColor\": \"#000000\","
			+ "\"fontFamily\": null}";

	@Test
	public void systemOutputInteraction() throws Exception {
		UInteraction interacao = new ScenarioFromText(String.format("[%s,%s]", targetInteraction, output)).firstState();
		List<UOutput> outputs = interacao.getOutputs();
		UOutput output = outputs.get(0);
		assertEquals("Alvo", interacao.getFixtureName());
		assertEquals(UUID.fromString("6063b665-b963-62dd-de91-195d0d6791ad"), interacao.getUuid());
		assertNull(interacao.getTransaction());

		assertEquals(UUID.fromString("a2df2c45-be51-27cc-ae8d-f2772ceaa6f4"), output.getUuid());
		assertEquals("saida", output.getFixtureName());
		assertEquals("Valor", output.getValue());

	}

	@Test
	public void targetInteraction() throws Exception {
		UInteraction interacao = new ScenarioFromText(String.format("[%s]", initialInteraction)).firstState();
		assertEquals("Fixture8Puzzle", interacao.getFixtureName());
		assertNull(interacao.getTransaction());
	}

	@Test
	public void userInputInteraction() throws Exception {
		UInteraction interacao = new ScenarioFromText(String.format("[%s,%s]", targetInteraction, input)).firstState();
		List<UInput> inputs = interacao.getInputs();
		UInput input = inputs.get(0);

		assertEquals("Alvo", interacao.getFixtureName());
		assertNull(interacao.getTransaction());

		assertEquals("entrada", input.getFixtureName());
		assertEquals(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42265"), input.getUuid());
		assertEquals("Valor", input.getValue());
	}

	@Test
	public void inputOutputInteraction() throws Exception {
		UInteraction interacao = new ScenarioFromText(
				String.format("[%s,%s,%s]", transaction, initialInteraction, targetInteraction)).firstState();
		UInteraction target = interacao.getTransaction().getTarget();
		assertNull(target.getTransaction());
		assertEquals(UUID.fromString("dcc6fc42-5fb4-d204-0d39-fbaf82c1cf85"), interacao.getTransaction().getUuid());
		assertEquals("Fixture8Puzzle", interacao.getFixtureName());
		assertEquals("Alvo", target.getFixtureName());
	}

//	@Test(expected = Exception.class)
//	public void createScenarioFromText_EmptyStringArgument_ShouldThrowException() throws Exception {
//		new ScenarioFromText("");
//	}
	
//	@Test(expected = Exception.class)
//	public void createScenarioFromText_EmptyGson_ShouldThrowException() throws Exception {
//		new ScenarioFromText("{}");
//	}
	
//	@Test(expected = Exception.class)
//	public void createScenarioFromText_EmptyArgument_ShouldThrowException() throws Exception {
//		new ScenarioFromText(String.format("[%s,%s,%s]", "", initialInteraction, targetInteraction));
//	}
	
//	@Test(expected = Exception.class)
//	public void createScenarioFromText_EmptyArgument_ShouldThrowException() throws Exception {
//		new ScenarioFromText(String.format("[%s,%s,%s]", transaction, initialInteraction, ""));
//	}

}
