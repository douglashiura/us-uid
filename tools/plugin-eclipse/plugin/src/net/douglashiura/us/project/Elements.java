package net.douglashiura.us.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.douglashiura.usuid.plugin.type.InputGeometry;
import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.plugin.type.OutputGeometry;
import net.douglashiura.usuid.plugin.type.Rateable;
import net.douglashiura.usuid.plugin.type.TransactionGeometry;

public class Elements {

	public static Map<String, Rateable> from(InteractionGeometry firstState) {
		HashMap<String, Rateable> map = new HashMap<String, Rateable>();
		ofInteraction(firstState, map);
		return map;
	}

	private static void ofInteraction(InteractionGeometry firstState, HashMap<String, Rateable> map) {
		map.put(firstState.getId(), firstState);
		for (InputGeometry aInput : firstState.getInputs())
			map.put(aInput.getId(), aInput);
		for (OutputGeometry aOutput : firstState.getOutputs())
			map.put(aOutput.getId(), aOutput);
		if (firstState.getTransaction() != null) {
			TransactionGeometry transaction = firstState.getTransaction();
			map.put(transaction.getId(), transaction);
			List<InteractionGeometry> transactions = transaction.getTargets();
			for (InteractionGeometry interactionGeometry : transactions) {
				ofInteraction(interactionGeometry, map);
			}
		}

	}

}
