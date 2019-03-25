package net.douglashiura.leb.uid.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class Scenarios {
	public static List<Scenario> getScenarios(Project project) throws IOException {
		List<Scenario> scenarios = new ArrayList<>();
		getAllRecursive(project, scenarios);
		return scenarios;
	}

	private static void getAllRecursive(Project project, List<Scenario> scenarios) throws IOException {
		scenarios.addAll(project.getScenarios());
		for (String folder : project.getFolders()) {
			getAllRecursive(project.enter(folder), scenarios);
		}
	}

}
