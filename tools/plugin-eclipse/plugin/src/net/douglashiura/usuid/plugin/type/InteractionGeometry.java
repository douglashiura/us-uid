package net.douglashiura.usuid.plugin.type;

import java.util.ArrayList;
import java.util.List;

public class InteractionGeometry extends DrawInteraction implements Rateable {
	private String model;
	private Geometry geometry;
	private TransactionGeometry transaction;
	private List<InputGeometry> inputs;
	private List<OutputGeometry> outputs;
	private String id;

	public InteractionGeometry(String id, Geometry geometry, String model) {
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

	public TransactionGeometry getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionGeometry aTarget) {
		this.transaction = aTarget;
	}

	public List<InputGeometry> getInputs() {
		return inputs;
	}

	public void addInput(InputGeometry input) {
		inputs.add(input);

	}

	public List<OutputGeometry> getOutputs() {
		return outputs;
	}

	public void addOutput(OutputGeometry output) {
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
		return InteractionGeometry.class;
	}

}
