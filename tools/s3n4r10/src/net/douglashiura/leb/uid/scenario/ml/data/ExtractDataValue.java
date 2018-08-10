package net.douglashiura.leb.uid.scenario.ml.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class ExtractDataValue {

	public static ArrayList<String> from(List<Data> instances, Nominal nominal) {
		Set<String> values = new HashSet<>();
		for (Data data : instances)
			values.add(nominal.extact(data));
		return new ArrayList<>(values);
	}

	public static Attribute makeAttribute(List<Data> instances, Nominal id, int index) {
		ArrayList<String> values = ExtractDataValue.from(instances, id);
		return new Attribute(id.getName(), values, index);
	}

	public static Instances intances(List<Data> instances) {
		Attribute scenario = makeAttribute(instances, new Scenario(), 0);
		Attribute id = makeAttribute(instances, new Id(), 1);
		Attribute parent = makeAttribute(instances, new Parent(), 2);
		Attribute type = makeAttribute(instances, new Type(), 3);
		Attribute value = makeAttribute(instances, new Value(), 4);
		Attribute fixture = makeAttribute(instances, new Fixture(), 5);
		Attribute startDistance = new Attribute("StartDistance", 6);
		Attribute endDistance = new Attribute("EndDistance", 7);
		Attribute deep = new Attribute("Deep", 8);
		Attribute inputs = new Attribute("Inputs", 9);
		Attribute outputs = new Attribute("Outputs", 10);
		Attribute elements = new Attribute("Elements", 11);
		ArrayList<Attribute> atributes = new ArrayList<>();
		atributes.add(scenario);
		atributes.add(id);
		atributes.add(parent);
		atributes.add(type);
		atributes.add(value);
		atributes.add(fixture);
		atributes.add(startDistance);
		atributes.add(endDistance);
		atributes.add(deep);
		atributes.add(inputs);
		atributes.add(outputs);
		atributes.add(elements);
		Instances isATraining = new Instances("dataSet", atributes, instances.size());
		for (Data data : instances) {
			Instance instance = new DenseInstance(12);
			instance.setValue(scenario, data.getScenario());
			instance.setValue(id, data.getId());
			instance.setValue(parent, data.getParent());
			instance.setValue(type, data.getType().toString());
			instance.setValue(value, data.getValue());
			instance.setValue(fixture, data.getFixture());
			instance.setValue(startDistance, data.getStartDistance());
			instance.setValue(endDistance, data.getEndDistance());
			instance.setValue(deep, data.getDeep());
			instance.setValue(inputs, data.getInputs());
			instance.setValue(outputs, data.getOutputs());
			instance.setValue(elements, data.getElements());
			isATraining.add(instance);
		}
		return isATraining;
	}

	interface Nominal {
		String extact(Data data);

		String getName();
	}

	static public abstract class AbstracNominal implements Nominal {
		public String getName() {
			return this.getClass().getSimpleName();
		}

	}

	static public class Id extends AbstracNominal {

		@Override
		public String extact(Data data) {
			return data.getId();
		}

	}

	static public class Fixture extends AbstracNominal {
		@Override
		public String extact(Data data) {
			return data.getFixture();
		}
	}

	static public class Parent extends AbstracNominal {

		@Override
		public String extact(Data data) {
			return data.getParent();
		}
	}

	static public class Value extends AbstracNominal {

		@Override
		public String extact(Data data) {
			return data.getValue();
		}
	}

	static public class Scenario extends AbstracNominal {

		@Override
		public String extact(Data data) {
			return data.getScenario();
		}
	}

	static public class Type extends AbstracNominal {

		@Override
		public String extact(Data data) {
			return data.getType().toString();
		}
	}

}
