package net.douglashiura.leb.uid.scenario.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.ScenarioFromText;
import net.douglashiura.us.Input;
import net.douglashiura.us.Interaction;
import net.douglashiura.us.Output;

public class OnSenarios {

	private List<Element> elements;

	public OnSenarios(Project project) throws IOException {
		elements = new ArrayList<>();
		loadAndReadScenarios(project);
	}

	private void loadAndReadScenarios(Project project) throws IOException {
		List<Scenario> scenarios = project.getScenaries();
		for (Scenario scenario : scenarios) {
			loadElements(scenario);
		}
		List<String> folders = project.getFolders();
		for (String folder : folders) {
			loadAndReadScenarios(project.enter(folder));
		}
	}

	private void loadElements(Scenario scenario) throws IOException {
		ScenarioFromText transformer = new ScenarioFromText(scenario.getDocument(), scenario.getVirtualName());
		try {
			Interaction interacao = transformer.firstState();
			loadElements(interacao, scenario.getVirtualName());
		} catch (Exception whitoutInteractions) {
		}
	}

	private void loadElements(Interaction interacao, String usuid) {
		List<Input> inputs = interacao.getInputs();
		for (Input input : inputs) {
			elements.add(new Element(usuid, Element.Type.INPUT, input.getValue(), input.getFixtureName()));
		}
		List<Output> outputs = interacao.getOutputs();
		for (Output output : outputs) {
			elements.add(new Element(usuid, Element.Type.OUTPUT, output.getValue(), output.getFixtureName()));
		}
		if (interacao.getTransaction() != null && interacao.getTransaction().getTarget() != null) {
			loadElements(interacao.getTransaction().getTarget(), usuid);
		}
	}

	public List<Element> getElements() {
		return elements;
	}

}
