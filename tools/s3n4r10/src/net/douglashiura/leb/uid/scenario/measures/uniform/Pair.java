package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.io.IOException;
import java.util.List;

import net.douglashiura.leb.uid.scenario.EmptyScenarioException;
import net.douglashiura.leb.uid.scenario.data.Scenario;
import net.douglashiura.leb.uid.scenario.model.InteractionTree;
import net.douglashiura.leb.uid.scenario.model.ScenarioFromText;

public class Pair {

	private AbsoluteUniformity uniform;
	private Scenario a;
	private Scenario b;

	public Pair(Scenario a, Scenario b) throws IOException, EmptyScenarioException {
		this.a = a;
		this.b = b;
		List<InteractionTree> scenarioA = new ScenarioFromText(a.getDocument()).getInteractionsUnsctrutured();
		List<InteractionTree> scenarioB = new ScenarioFromText(b.getDocument()).getInteractionsUnsctrutured();
		uniform = new AbsoluteUniformity(scenarioA, scenarioB);

	}

	public AbsoluteUniformity getUniform() {
		return uniform;
	}

	public Scenario getA() {
		return a;
	}

	public Scenario getB() {
		return b;
	}
}
