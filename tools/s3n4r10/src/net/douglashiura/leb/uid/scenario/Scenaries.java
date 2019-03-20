package net.douglashiura.leb.uid.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class Scenaries {
	public static List<Scenario> getScenarios(Project project) throws IOException {
		List<Scenario> scenaries = new ArrayList<>();
		getAllRecursive(project, scenaries);
		return scenaries;
	}

	private static void getAllRecursive(Project project, List<Scenario> scenaries) throws IOException {
		scenaries.addAll(project.getScenaries());
		for (String folder : project.getFolders()) {
			getAllRecursive(project.enter(folder), scenaries);
		}
	}

}
