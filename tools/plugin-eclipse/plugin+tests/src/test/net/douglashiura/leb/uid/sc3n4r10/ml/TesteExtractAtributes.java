package test.net.douglashiura.leb.uid.sc3n4r10.ml;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.ml.data.Data;
import net.douglashiura.leb.uid.scenario.ml.data.ExtractDataValue;
import weka.core.Attribute;

public class TesteExtractAtributes {

	@Test
	public void extractAtibutesValues() {
		Data data = new Data();
		data.setUuid(UUID.fromString("dcc6fc42-5fb4-d204-0d39-fbaf82c1cf85"));
		Data data2 = new Data();
		data2.setUuid(UUID.fromString("dcc6fc42-5fb4-d204-0d39-fbaf82c1cf85"));
		List<Data> instances = Arrays.asList(data, data2);
		ArrayList<String> values = ExtractDataValue.from(instances, new ExtractDataValue.Id());
		assertEquals(1, values.size());
		assertEquals("dcc6fc42-5fb4-d204-0d39-fbaf82c1cf85", values.get(0));
	}

	@Test
	public void atributesNames() {
		assertEquals("Id", new ExtractDataValue.Id().getName());
		assertEquals("Fixture", new ExtractDataValue.Fixture().getName());
		assertEquals("Parent", new ExtractDataValue.Parent().getName());
		assertEquals("Scenario", new ExtractDataValue.Scenario().getName());
		assertEquals("Type", new ExtractDataValue.Type().getName());
		assertEquals("Value", new ExtractDataValue.Value().getName());
		
	}

	@Test
	public void makeAtribute() throws Exception {
		Data data = new Data();
		data.setUuid(UUID.fromString("dcc6fc42-5fb4-d204-0d39-fbaf82c1cf85"));
		List<Data> instances = Arrays.asList(data);
		Attribute attribute = ExtractDataValue.makeAttribute(instances, new ExtractDataValue.Id(), 0);
		assertEquals("dcc6fc42-5fb4-d204-0d39-fbaf82c1cf85", attribute.enumerateValues().nextElement());
	}

}
