package net.douglashiura.leb.uid.scenario.measures.uniform;

import net.douglashiura.leb.uid.scenario.Interaction;

public class AbsoluteUniformity {
	private ElementsCluster clusterA;
	private ElementsCluster clusterB;
	private Counter counter;

	public AbsoluteUniformity(Interaction scenarioA, Interaction scenarioB) {
		clusterA = new ElementsCluster(scenarioA);
		clusterB = new ElementsCluster(scenarioB);
		counter = new Counter(clusterA, clusterB);
	}

	public RelativeUniformity getRelativeUniformity() {
		RelativeUniformity relative = new RelativeUniformity();
		Integer uniformOutputs = getUniformOutputs();
		Integer uniformIntputs = getInputs();
		Integer nonUniformInputs = getInputs() - uniformIntputs;
		Integer nonUniformOutputs = getOutputs() - uniformOutputs;
		relative.setNonUniformInput(nonUniformInputs);
		relative.setNonUniformOutput(nonUniformOutputs);
		relative.setUniformInput(uniformIntputs);
		relative.setUniformOutput(uniformOutputs);
		return relative;
	}

	public Integer getInputs() {
		return clusterA.getInputs().size();
	}

	public Integer getOutputs() {
		return clusterA.getOutputs().size();
	}

	public Integer getUniformIntputs() {
		return counter.getUniformInputs().size();
	}

	public Integer getUniformOutputs() {
		return counter.getUniformOutputs().size();
	}
}