package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.xml.sax.SAXException;

import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.ScenarioFromText;
import net.douglashiura.leb.uid.scenario.data.Scenario;
import net.douglashiura.leb.uid.scenario.measures.uniform.similarity.Similarity;
import net.douglashiura.leb.uid.scenario.measures.uniform.similarity.Similarity.SimilarityCosine;
import net.douglashiura.leb.uid.scenario.measures.uniform.similarity.Similarity.SimilarityJaccard;
import net.douglashiura.leb.uid.scenario.measures.uniform.similarity.Similarity.SimilarityMongeElkan;
import net.douglashiura.leb.uid.scenario.measures.uniform.similarity.SimilarityDocuments;

public class Pair {

	private Scenario a;
	private Scenario b;
	private AbsoluteUniformity uniform;
	private int i;
	private int j;
	private Similarity similiarity;
	private SimilarityCosine similiarityCosine;
	private SimilarityMongeElkan similiarityMongeElkan;
	private SimilarityJaccard similiarityJaccard;
	private SimilarityDocuments similiarityDocuments;

	public Pair(Scenario a, Scenario b) throws IOException, SAXException {
		this.a = a;
		this.b = b;
		Interaction scenarioA = new ScenarioFromText(a.getDocument(), a.getFile().getAbsolutePath()).firstState();
		Interaction scenarioB = new ScenarioFromText(b.getDocument(), a.getFile().getAbsolutePath()).firstState();
		uniform = new AbsoluteUniformity(scenarioA, scenarioB);
		similiarity = new Similarity(scenarioA, scenarioB);
		similiarityCosine = new SimilarityCosine(scenarioA, scenarioB);
		similiarityMongeElkan = new SimilarityMongeElkan(scenarioA, scenarioB);
		similiarityJaccard = new SimilarityJaccard(scenarioA, scenarioB);
		similiarityDocuments = new SimilarityDocuments(scenarioA, scenarioB);

	}

	public void print() {
		int inputs = uniform.getInputs();
		int nonUniformInputs = inputs - uniform.getUniformIntputs();
		int outputs = uniform.getOutputs();
		int nonUniformOutputs = outputs - uniform.getUniformOutputs();

		System.out.printf(new Locale("pt", "BR"), "A%dXA%d	%d	%d	%d	%d	%f	%f	%f	%f	%f	%s	%s\n", i, j,
				uniform.getUniformIntputs(), nonUniformInputs, uniform.getUniformOutputs(), nonUniformOutputs,
				uniform.getRelativeUniformity().getUniformity(), similiarity.getSimilarity(),
				similiarityCosine.getSimilarity(), similiarityJaccard.getSimilarity(),
				similiarityMongeElkan.getSimilarity(), similiarityDocuments.getSimilarity(),
				similiarityDocuments.getIdentical());
		// Inputs | Outputs | Inputs uniforms | Outputs uniforms | Relative
		// uniformity|Relative uniformity2| cosineSimilarity
		// AXB
	}

	public Scenario getA() {
		return a;
	}

	public Scenario getB() {
		return b;
	}

	public static List<Pair> pairs(List<Scenario> scenaries) throws IOException, SAXException {
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < scenaries.size(); i++) {
			for (int j = 0; j < scenaries.size(); j++) {
				if (i != j) {
					pairs.add(new Pair(scenaries.get(i), scenaries.get(j)));
				}
			}
		}
		return pairs;
	}

}
