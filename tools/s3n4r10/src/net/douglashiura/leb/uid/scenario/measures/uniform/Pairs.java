package net.douglashiura.leb.uid.scenario.measures.uniform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.EmptyScenarioException;
import net.douglashiura.leb.uid.scenario.data.Scenario;

public class Pairs {
	public static List<Pair> pairs(List<Scenario> scenaries) throws IOException {
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < scenaries.size(); i++) {
			for (int j = 0; j < scenaries.size(); j++) {
				if (i != j) {
					try {
						pairs.add(new Pair(scenaries.get(i), scenaries.get(j)));
					} catch (EmptyScenarioException e) {

					}
				}
			}
		}
		return pairs;
	}
}
