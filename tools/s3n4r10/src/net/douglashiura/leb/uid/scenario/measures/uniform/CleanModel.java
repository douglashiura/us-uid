package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonSyntaxException;

import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class CleanModel {

	private static List<Scenario> scenaries;

	public static void main(String[] args) throws JsonSyntaxException, IOException {
		scenaries = new ArrayList<>();
		getScenarios(Project.get());
		for (Scenario scenario : scenaries) {
			clean(scenario);
		}
		
	}

	private static void clean(Scenario scenario) throws JsonSyntaxException, IOException {
		String documento = scenario.getDocument();
		System.out.println(scenario.getFile());
		documento=documento.replaceAll("\"model\"", "\"model\": \"\", \"model2\"");
		scenario.write(documento.getBytes());
		
		
	}

	

	private static void getScenarios(Project project) {
		scenaries.addAll(project.getScenaries());
		for (String folder : project.getFolders()) {
			getScenarios(project.enter(folder));
		}
	}

}
