package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.util.List;

public class Average {

	private Float uniformity;

	public Average(List<Pair> pairs) {
		Float total = 0f;
		for (Pair pair : pairs) {
			total += pair.getUniform().getRelativeUniformity().getUniformity();
		}
		uniformity = total / pairs.size();
	}

	public Float getUniformity() {
		return uniformity;
	}

}
