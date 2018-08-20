package net.douglashiura.usuid.plugin.type;

import java.util.ArrayList;
import java.util.List;

public class Interaction extends DrawInteraction implements Rateable {
	private String model;
	private Geometry geometry;
	private Transaction transaction;
	private List<Input> inputs;
	private List<Output> outputs;
	private String id;

	public Interaction(String id, Geometry geometry, String model) {
		this.id = id;
		this.geometry = geometry;
		this.model = model;
		this.inputs = new ArrayList<>();
		this.outputs = new ArrayList<>();
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public String getFixture() {
		return model;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction aTarget) {
		this.transaction = aTarget;
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public void addInput(Input input) {
		inputs.add(input);

	}

	public List<Output> getOutputs() {
		return outputs;
	}

	public void addOutput(Output output) {
		outputs.add(output);
	}

	public String getId() {
		return id;
	}
	
	@Override
	public String getValue() {
		return "";
	}
	@Override
	public Class<?> getType() {
		return Interaction.class;
	}

}
