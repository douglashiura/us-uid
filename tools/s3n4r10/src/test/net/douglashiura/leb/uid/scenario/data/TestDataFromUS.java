package test.net.douglashiura.leb.uid.scenario.data;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.Input;
import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.Output;
import net.douglashiura.leb.uid.scenario.ml.data.Data;
import net.douglashiura.leb.uid.scenario.ml.data.DataFromUS;

public class TestDataFromUS {

	@Test
	public void readInput() throws Exception {
		Interaction first = new Interaction("id", "fixture");
		Input input = new Input("id", "fixture1", "value");
		first.addInput(input);
		List<Data> instances = DataFromUS.read(first, "file");
		assertEquals(1, instances.size());
		assertEquals("id", instances.get(0).getId());
		assertEquals(new Integer(0), instances.get(0).getEndDistance());
		assertEquals(new Integer(1), instances.get(0).getInputs());
		assertEquals(new Integer(0), instances.get(0).getOutputs());
		assertEquals(new Integer(1), instances.get(0).getElements());
		assertEquals(new Integer(0), instances.get(0).getStartDistance());
		assertEquals(new Integer(0), instances.get(0).getDeep());
		assertEquals("file", instances.get(0).getScenario());
		assertEquals("fixture1", instances.get(0).getFixture());
		assertEquals("value", instances.get(0).getValue());
		assertEquals(Data.Type.INPUT, instances.get(0).getType());
	}

	@Test
	public void readOutput() throws Exception {
		Interaction first = new Interaction("id", "fixture");
		Output output = new Output("id", "fixture1", "value");
		first.addOutput(output);
		List<Data> instances = DataFromUS.read(first, "file");
		assertEquals(1, instances.size());
		assertEquals("id", instances.get(0).getId());
		assertEquals(new Integer(0), instances.get(0).getEndDistance());
		assertEquals(new Integer(0), instances.get(0).getInputs());
		assertEquals(new Integer(1), instances.get(0).getOutputs());
		assertEquals(new Integer(1), instances.get(0).getElements());
		assertEquals(new Integer(0), instances.get(0).getStartDistance());
		assertEquals(new Integer(0), instances.get(0).getDeep());
		assertEquals("file", instances.get(0).getScenario());
		assertEquals("fixture1", instances.get(0).getFixture());
		assertEquals("value", instances.get(0).getValue());
		assertEquals(Data.Type.OUTPUT, instances.get(0).getType());

	}

}
