package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import net.douglashiura.leb.uid.scenario.data.Project;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class UniformScenarios {
	private List<Scenario> scenaries;
	private List<Pair> pairs;
	private UniformValues instances;

	public UniformScenarios() throws IOException, SAXException {
		scenaries = new ArrayList<>();
		getScenarios(Project.get());
		pairs = Pair.pairs(scenaries);
		instances = new UniformValues(scenaries);

	}

	private void printValues() {
		System.out.println("	Inputs u|Inputs nonu|Outputs un|Outputs	nonu| Relative uniformity|Relative uniformity2|cosineSimilarity|Jaccard|MongeElkan|Documents");
		for (Pair pair : pairs) {
			pair.print();
		}

	}

	private void printInstances() {
		System.out.println();
		System.out.println(instances);
	}

	private void printNames() {
		int i = 0;
		for (Scenario scenario : scenaries) {
			System.out.printf("A%d	%s\n", i++, scenario.getFile().getAbsolutePath());
		}
	}

	private void getScenarios(Project project) {
		scenaries.addAll(project.getScenaries());
		for (String folder : project.getFolders()) {
			getScenarios(project.enter(folder));
		}
	}

	public static void main(String[] args) throws IOException, SAXException {
		UniformScenarios uniformScenarios = new UniformScenarios();
		uniformScenarios.printNames();
		uniformScenarios.printValues();
		uniformScenarios.printInstances();

	}

}
