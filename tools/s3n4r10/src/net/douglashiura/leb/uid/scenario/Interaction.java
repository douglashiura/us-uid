package net.douglashiura.leb.uid.scenario;

import java.util.ArrayList;
import java.util.List;

public class Interaction {
	private String fixture;
	private Transaction transaction;
	private List<Input> inputs;
	private List<Output> outputs;
	private String id;

	public Interaction(String id, String fixture) {
		this.id = id;
		this.fixture = fixture;
		this.inputs = new ArrayList<>();
		this.outputs = new ArrayList<>();
	}

	public String getFixture() {
		return fixture;
	}
	public String getId() {
		return id;
	}
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
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

	

}
