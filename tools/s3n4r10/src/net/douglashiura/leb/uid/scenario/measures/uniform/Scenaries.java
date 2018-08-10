package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class Scenaries {
	public static List<Scenario> getScenarios(Project project) {
		List<Scenario> scenaries = new ArrayList<>();
		getAllRecursive(project, scenaries);
		return scenaries;
	}

	private static void getAllRecursive(Project project, List<Scenario> scenaries) {
		scenaries.addAll(project.getScenaries());
		for (String folder : project.getFolders()) {
			getAllRecursive(project.enter(folder), scenaries);
		}
	}

}
