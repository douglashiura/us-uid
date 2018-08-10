package test.net.douglashiura.leb.uid.scenario.data;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.ml.data.DataInteraction;
import net.douglashiura.leb.uid.scenario.ml.data.DataInteractionFromUS;

public class TestDataInteractionFromUS {

	@Test
	public void read() throws Exception {
		Interaction first = new Interaction("id", "fixture");
		List<DataInteraction> instances = DataInteractionFromUS.read(first, "file");
		assertEquals(1, instances.size());
		assertEquals("id", instances.get(0).getId());
		assertEquals(new Integer(0), instances.get(0).getEndDistance());
		assertEquals(new Integer(0), instances.get(0).getInputs());
		assertEquals(new Integer(0), instances.get(0).getOutputs());
		assertEquals(new Integer(0), instances.get(0).getElements());
		assertEquals(new Integer(0), instances.get(0).getStartDistance());
		assertEquals(new Integer(0), instances.get(0).getDeep());
		assertEquals("file", instances.get(0).getScenario());
		assertEquals("fixture", instances.get(0).getFixture());
	}

}
