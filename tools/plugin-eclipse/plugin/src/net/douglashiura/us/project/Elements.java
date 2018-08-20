 package net.douglashiura.us.project;

import java.util.HashMap;
import java.util.Map;

import net.douglashiura.usuid.plugin.type.Input;
import net.douglashiura.usuid.plugin.type.Interaction;
import net.douglashiura.usuid.plugin.type.Output;
import net.douglashiura.usuid.plugin.type.Rateable;
import net.douglashiura.usuid.plugin.type.Transaction;

public class Elements {

	 public static Map<String, Rateable> from(Interaction firstState) {
		HashMap<String, Rateable> map = new HashMap<String, Rateable>();
		ofInteraction(firstState, map);
		return map;
	}

	private static void ofInteraction(Interaction firstState, HashMap<String, Rateable> map) {
		map.put(firstState.getId(), firstState);
		for (Input aInput : firstState.getInputs())
			map.put(aInput.getId(), aInput);
		for (Output aOutput : firstState.getOutputs())
			map.put(aOutput.getId(), aOutput);
		if (firstState.getTransaction() != null) {
			Transaction transaction = firstState.getTransaction();
			map.put(transaction.getId(), transaction);
			ofInteraction(transaction.getTarget(), map);
		}

	}

}
