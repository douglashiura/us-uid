package test.net.douglashiura.us;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;

import net.douglashiura.us.Input;
import net.douglashiura.us.Interaction;
import net.douglashiura.us.Output;
import net.douglashiura.us.run.SmartJson;

public class TestJsonSmartFromScenario {

	@Test
	public void aInteraction() throws Exception {
		String smartJson = "{\"heads\":{\"file\":\"aFile.us\",\"path\":1},\"elemtens\":[{\"type\":\"br.ufsc.leb.uid.scenario.Interacao\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName=\":\"Origin\"}]}";
		Interaction interaction = new Interaction(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42265"), "Origin");
		assertEquals(smartJson, SmartJson.from(interaction, "aFile.us", 1));
	}

	@Test
	public void aInteractionWhithAInput() throws Exception {
		String input = "{\"type\":\"br.ufsc.leb.uid.scenario.UserInput\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42268\",\"composite\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName\":\"input\",\"value\":\"value\"}";
		String smartJson = "{\"heads\":{\"file\":\"aFile.us\",\"path\":1},\"elemtens\":[{\"type\":\"br.ufsc.leb.uid.scenario.Interacao\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName=\":\"Origin\"},"
				+ input + "]}";
		Interaction interaction = new Interaction(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42265"), "Origin");
		interaction.addInput(new Input(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42268"), "input", "value"));
		assertEquals(smartJson, SmartJson.from(interaction, "aFile.us", 1));
	}

	@Test
	public void aInteractionWhithAOutput() throws Exception {
		String output = "{\"type\":\"br.ufsc.leb.uid.scenario.SystemOutput\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42268\",\"composite\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName\":\"input\",\"value\":\"value\"}";
		String smartJson = "{\"heads\":{\"file\":\"aFile.us\",\"path\":1},\"elemtens\":[{\"type\":\"br.ufsc.leb.uid.scenario.Interacao\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName=\":\"Origin\"},"
				+ output + "]}";
		Interaction interaction = new Interaction(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42265"), "Origin");
		interaction.addOutput(new Output(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42268"), "input", "value"));
		assertEquals(smartJson, SmartJson.from(interaction, "aFile.us", 1));
	}

	@Test
	public void aInteractionWhithTwoInput() throws Exception {
		String input = "{\"type\":\"br.ufsc.leb.uid.scenario.UserInput\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42268\",\"composite\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName\":\"input\",\"value\":\"value\"}";
		String input2 = ",{\"type\":\"br.ufsc.leb.uid.scenario.UserInput\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42269\",\"composite\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName\":\"input2\",\"value\":\"value2\"}";
		String smartJson = "{\"heads\":{\"file\":\"aFile.us\",\"path\":1},\"elemtens\":[{\"type\":\"br.ufsc.leb.uid.scenario.Interacao\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName=\":\"Origin\"},"
				+ input + input2 + "]}";
		Interaction interaction = new Interaction(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42265"), "Origin");
		interaction.addInput(new Input(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42268"), "input", "value"));
		interaction.addInput(new Input(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42269"), "input2", "value2"));
		assertEquals(smartJson, SmartJson.from(interaction, "aFile.us", 1));
	}

	@Test
	public void twoInteraction() throws Exception {
		String interaction1 = "{\"type\":\"br.ufsc.leb.uid.scenario.Interacao\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"fixtureName=\":\"Origin\"},";
		String transaction = "{\"type\":\"br.ufsc.leb.uid.scenario.Transaction\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42267\",\"source=\":\"deb51e97-bd3f-82c3-74d2-ef1815a42265\",\"target\":\"deb51e97-bd3f-82c3-74d2-ef1815a42266\"},";
		String interaction2 = "{\"type\":\"br.ufsc.leb.uid.scenario.Interacao\",\"id\":\"deb51e97-bd3f-82c3-74d2-ef1815a42266\",\"fixtureName=\":\"Target\"}";
		String smartJson = "{\"heads\":{\"file\":\"aFile.us\",\"path\":1},\"elemtens\":[" + interaction1 + transaction
				+ interaction2 + "]}";
		Interaction aInteraction = new Interaction(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42265"), "Origin");
		Interaction aInteraction2 = new Interaction(UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42266"), "Target");
		aInteraction.to(aInteraction2, UUID.fromString("deb51e97-bd3f-82c3-74d2-ef1815a42267"));
		assertEquals(smartJson, SmartJson.from(aInteraction, "aFile.us", 1));
	}

}
