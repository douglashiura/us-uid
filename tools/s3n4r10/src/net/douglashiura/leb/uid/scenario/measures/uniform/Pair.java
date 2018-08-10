package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.io.IOException;

import org.xml.sax.SAXException;

import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.ScenarioFromText;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class Pair {

	private AbsoluteUniformity uniform;
	private Scenario a;
	private Scenario b;

	public Pair(Scenario a, Scenario b) throws IOException, SAXException {
		this.a = a;
		this.b = b;
		Interaction scenarioA = new ScenarioFromText(a.getDocument(), a.getFile().getAbsolutePath()).firstState();
		Interaction scenarioB = new ScenarioFromText(b.getDocument(), a.getFile().getAbsolutePath()).firstState();
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
