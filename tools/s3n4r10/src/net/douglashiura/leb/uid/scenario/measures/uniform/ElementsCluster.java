package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.util.ArrayList;
import java.util.List;

import net.douglashiura.us.serial.Input;
import net.douglashiura.us.serial.Interaction;
import net.douglashiura.us.serial.Output;

public class ElementsCluster {
	private List<Input> inputs;
	private List<Output> outputs;

	public ElementsCluster(Interaction scenario) {
		inputs = new ArrayList<>();
		outputs = new ArrayList<>();
		readAll(scenario);

	}
	private void readAll(Interaction scenario) {
		inputs.addAll(scenario.getInputs());
		outputs.addAll(scenario.getOutputs());
		if (scenario.getTransaction() != null) {
			readAll(scenario.getTransaction().getTarget());
		}
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public List<Output> getOutputs() {
		return outputs;
	}

}
