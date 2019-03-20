package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.io.IOException;

import net.douglashiura.leb.uid.scenario.EmptyScenarioException;
import net.douglashiura.leb.uid.scenario.ScenarioFromText;
import net.douglashiura.leb.uid.scenario.data.Scenario;
import net.douglashiura.us.serial.Interaction;

public class Pair {

	private AbsoluteUniformity uniform;
	private Scenario a;
	private Scenario b;

	public Pair(Scenario a, Scenario b) throws IOException, EmptyScenarioException {
		this.a = a;
		this.b = b;
		Interaction scenarioA = new ScenarioFromText(a.getDocument(), a.getVirtualName()).firstState();
		Interaction scenarioB = new ScenarioFromText(b.getDocument(), a.getVirtualName()).firstState();
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
