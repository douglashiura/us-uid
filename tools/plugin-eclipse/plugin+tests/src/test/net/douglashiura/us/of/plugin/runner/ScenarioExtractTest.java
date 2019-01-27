package test.net.douglashiura.us.of.plugin.runner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.plugin.type.Scenario;

public class ScenarioExtractTest {

	@Test
	public void tree() throws Exception {
		InputStream source = this.getClass().getResourceAsStream("Tree.us");
		byte[] bytes = new byte[source.available()];
		source.read(bytes);
		String jsonText = new String(bytes);
		source.close();
		Scenario scenario = new Scenario(jsonText);
		InteractionGeometry origin = scenario.starts().get(0);
		assertEquals(1, scenario.starts().size());
		assertNotNull(origin);
		assertEquals("Origin", origin.getFixtureName());
		assertNotNull(origin.getTransaction());
		assertNull(origin.getTransaction().getTargets().get(0).getTransaction());
		assertEquals(2, origin.getTransaction().getTargets().size());
		assertEquals("Target2", origin.getTransaction().getTargets().get(0).getFixtureName());
		assertEquals("Target1", origin.getTransaction().getTargets().get(1).getFixtureName());
	}

	@Test
	public void twoOrigin() throws Exception {
		InputStream source = this.getClass().getResourceAsStream("TwoOrigin.us");
		byte[] bytes = new byte[source.available()];
		source.read(bytes);
		String jsonText = new String(bytes);
		source.close();
		Scenario scenario = new Scenario(jsonText);
		List<InteractionGeometry> origins = scenario.starts();
		InteractionGeometry origin = origins.get(0);
		InteractionGeometry origin1 = origins.get(1);
		assertEquals(2, scenario.starts().size());
		assertEquals("Origin2", origin.getFixtureName());
		assertNotNull(origin.getTransaction());
		assertNull(origin.getTransaction().getTargets().get(0).getTransaction());
		assertEquals(1, origin.getTransaction().getTargets().size());
		assertEquals("Target", origin1.getTransaction().getTargets().get(0).getFixtureName());
		assertEquals("Source1", origin1.getFixtureName());
		assertNotNull(origin1.getTransaction());
		assertNull(origin1.getTransaction().getTargets().get(0).getTransaction());
		assertEquals(1, origin1.getTransaction().getTargets().size());
		assertEquals("Target", origin1.getTransaction().getTargets().get(0).getFixtureName());

	}

	@Test
	public void anInteraction() throws Exception {
		InputStream source = this.getClass().getResourceAsStream("AnInteraction.us");
		byte[] bytes = new byte[source.available()];
		source.read(bytes);
		String jsonText = new String(bytes);
		source.close();
		Scenario scenario = new Scenario(jsonText);
		InteractionGeometry origin = scenario.starts().get(0);
		assertEquals(1, scenario.starts().size());
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
		InteractionGeometry origin = scenario.starts().get(0);
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
		InteractionGeometry origin = scenario.starts().get(0);
		assertEquals(1, scenario.starts().size());
		assertNotNull(origin);
		assertEquals("Origin", origin.getFixtureName());
		assertNull(origin.getTransaction());
		assertEquals(2, origin.getInputs().size());
	}

}
