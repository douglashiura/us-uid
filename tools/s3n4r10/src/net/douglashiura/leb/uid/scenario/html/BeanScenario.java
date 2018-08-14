package net.douglashiura.leb.uid.scenario.html;

import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class BeanScenario {

	private List<Scenario> scenarios;

	public BeanScenario() {
		scenarios = new ArrayList<>();
		getScenarios(Project.get());
	}

	private void getScenarios(Project project) {
		scenarios.addAll(project.getScenaries());
		for (String folder : project.getFolders()) {
			getScenarios(project.enter(folder));
		}
	}

	public List<Scenario> getScenarios() {
		return scenarios;
	}

}
