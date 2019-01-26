package net.douglashiura.us;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Interaction {

	private UUID uuid;
	private String fixtureName;
	private Transaction transaction;
	private List<Input> inputs;
	private List<Output> outputs;

	public Interaction(UUID uuid, String fixtureName) {
		this.uuid = uuid;
		this.fixtureName = fixtureName;
		this.inputs = new ArrayList<>();
		this.outputs = new ArrayList<>();
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getFixtureName() {
		return fixtureName;
	}

	public Transaction getTransaction() {
		return transaction;
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

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}