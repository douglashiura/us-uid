package test.net.douglashiura.us.from.plugin.runner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;

import org.junit.Test;

import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.plugin.type.Scenario;

public class TreeScenarioExtractTest {

	@Test
	public void tree() throws Exception {
		InputStream source = this.getClass().getResourceAsStream("Tree.us");
		byte[] bytes = new byte[source.available()];
		source.read(bytes);
		String jsonText = new String(bytes);
		source.close();
		Scenario scenario = new Scenario(jsonText);
		InteractionGeometry origin = scenario.firstState();
		assertNotNull(origin);
		assertEquals("Origin", origin.getFixtureName());
		assertNotNull(origin.getTransaction());
		assertEquals(2, origin.getTransaction().getTargets().size());
		assertEquals("Target1", origin.getTransaction().getTargets().get(0).getFixtureName());
		assertEquals("Target2", origin.getTransaction().getTargets().get(1).getFixtureName());
	}

	@Test
	public void anInteraction() throws Exception {
		InputStream source = this.getClass().getResourceAsStream("AnInteraction.us");
		byte[] bytes = new byte[source.available()];
		source.read(bytes);
		String jsonText = new String(bytes);
		source.close();
		Scenario scenario = new Scenario(jsonText);
		InteractionGeometry origin = scenario.firstState();
		assertNotNull(origin);
		assertEquals("Origin", origin.getFixtureName());
		assertNull(origin.getTransaction());
	}

	@Test
	public void twoInteractions() throws Exception {
		InputStream source = this.getClass().getResourceAsStream("TwoInteractions.us");
		byte[] bytes = new byte[source.available()];
		source.read(bytes);
		String jsonText = new String(bytes);
		source.close();
		Scenario scenario = new Scenario(jsonText);
		InteractionGeometry origin = scenario.firstState();
		assertNotNull(origin);
		assertEquals("Origin", origin.getFixtureName());
		assertNotNull(origin.getTransaction());
		assertEquals(1, origin.getTransaction().getTargets().size());
		assertEquals("Target", origin.getTransaction().getTargets().get(0).getFixtureName());
	}

	@Test
	public void twoInputs() throws Exception {
		InputStream source = this.getClass().getResourceAsStream("TwoInputs.us");
		byte[] bytes = new byte[source.available()];
		source.read(bytes);
		String jsonText = new String(bytes);
		source.close();
		Scenario scenario = new Scenario(jsonText);
		InteractionGeometry origin = scenario.firstState();
		assertNotNull(origin);
		assertEquals("Origin", origin.getFixtureName());
		assertNull(origin.getTransaction());
		assertEquals(2, origin.getInputs().size());
	}
}
