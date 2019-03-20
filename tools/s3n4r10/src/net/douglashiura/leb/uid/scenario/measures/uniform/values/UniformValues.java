package net.douglashiura.leb.uid.scenario.measures.uniform.values;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import net.douglashiura.leb.uid.scenario.EmptyScenarioException;
import net.douglashiura.leb.uid.scenario.ScenarioFromText;
import net.douglashiura.leb.uid.scenario.data.Scenario;
import net.douglashiura.us.serial.Input;
import net.douglashiura.us.serial.Interaction;
import net.douglashiura.us.serial.Output;

public class UniformValues {

	private HashMap<String, Integer> instances;

	public UniformValues(List<Scenario> scenaries) throws IOException, EmptyScenarioException {
		instances = new HashMap<>();
		for (Scenario scenario : scenaries) {
			Interaction interaction = new ScenarioFromText(scenario.getDocument(),scenario.getVirtualName()).firstState();
			recursiveReader(interaction);
		}

	}

	private void recursiveReader(Interaction interaction) {
		for (Input input : interaction.getInputs()) {
			Integer total = instances.getOrDefault(input.getValue(), 0);
			String valor=input.getValue().trim();
			instances.put(valor, ++total);
		}
		for (Output output : interaction.getOutputs()) {
			Integer total = instances.getOrDefault(output.getValue(), 0);
			String valor=output.getValue().trim();
			instances.put(valor, ++total);
		}
		if (interaction.getTransaction() != null) {
			recursiveReader(interaction.getTransaction().getTarget());
		}
	}

	@Override
	public String toString() {
		StringBuffer values = new StringBuffer();
		for (String key : instances.keySet()) {
			values.append("\n" + key + "	" + instances.get(key));
		}
		return "Instances	Frequency"+values;
	}

}
