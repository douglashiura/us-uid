package test.net.douglashiura.leb.uid.scenario.ml;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.ml.data.Data;
import net.douglashiura.leb.uid.scenario.ml.data.Data.Type;
import net.douglashiura.leb.uid.scenario.ml.data.ExtractDataValue;
import weka.core.Instances;

public class TestExtractInstancesFromData {

	@Test
	public void instances() {
		Data data = new Data();
		data.setScenario("scenario x");
		data.setId("id x");
		data.setParent("parent x");
		data.setType(Type.INPUT);
		data.setValue("value x");
		data.setFixture("fixture x");
		data.setStartDistance(1);
		data.setEndDistance(2);
		data.setDeep(3);
		data.setInputs(4);
		data.setOutputs(5);
		data.setElements(6);
		List<Data> instances = Arrays.asList(data);
		Instances train = ExtractDataValue.intances(instances);
		assertEquals("Scenario", train.attribute(0).name());
		assertEquals("Id", train.attribute(1).name());
		assertEquals("Parent", train.attribute(2).name());
		assertEquals("Type", train.attribute(3).name());
		assertEquals("Value", train.attribute(4).name());
		assertEquals("Fixture", train.attribute(5).name());
		assertEquals("StartDistance", train.attribute(6).name());
		assertEquals("EndDistance", train.attribute(7).name());
		assertEquals("Deep", train.attribute(8).name());
		assertEquals("Inputs", train.attribute(9).name());
		assertEquals("Outputs", train.attribute(10).name());
		assertEquals("Elements", train.attribute(11).name());
		assertEquals("scenario x", train.get(0).stringValue(0));

	}

}
