package net.douglashiura.us;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class UScenario {

	private static final Object INTERACTION = "br.ufsc.leb.uid.scenario.Interacao";
	private static final Object OUTPUT = "br.ufsc.leb.uid.scenario.SystemOutput";
	private static final Object INPUT = "br.ufsc.leb.uid.scenario.UserInput";
	private static final Object TRANSACTION = "br.ufsc.leb.uid.scenario.Transaction";
	private HashMap<String, UInteraction> interactions;
	private List<Map<String, ?>> elements;
	private UInteraction first;

	@SuppressWarnings("unchecked")
	public UScenario(String text) {
		Gson gson = new Gson();
		this.elements = gson.fromJson(text, List.class);
		this.interactions = new HashMap<String, UInteraction>();
		extractInteractons();
		extractInputs();
		extractOutputs();
		extractTransaction();
		first = extractFirstState();
	}

	private UInteraction extractFirstState() {
		Collection<UInteraction> states = interactions.values();
		for (UInteraction aState : states) {
			if (nonArrival(aState)) {
				return aState;
			}
		}
		return null;
	}

	private boolean nonArrival(UInteraction nonTarget) {
		for (UInteraction aState : interactions.values()) {
			UTransaction transaction = aState.getTransaction();
			if (transaction != null && nonTarget.equals(transaction.getTarget())) {
				return false;
			}
		}
		return true;

	}

	private void extractOutputs() {
		for (Map<String, ?> object : elements) {
			if (OUTPUT.equals(object.get("type"))) {
				UInteraction composite = interactions.get(object.get("composite").toString());
				UOutput output = new UOutput(extractId(object), extractModel(object), extractValue(object));
				composite.addOutput(output);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void extractTransaction() {
		for (Map<String, ?> object : elements) {
			if (TRANSACTION.equals(object.get("type"))) {
				Object source = ((Map<String, ?>) object.get("source")).get("node");
				Object target = ((Map<String, ?>) object.get("target")).get("node");
				UInteraction aSource = interactions.get(source);
				UInteraction aTarget = interactions.get(target);
				UTransaction transaction = new UTransaction(extractId(object), aSource, aTarget);
				aSource.setTransaction(transaction);
			}
		}
	}

	private void extractInputs() {
		for (Map<String, ?> object : elements) {
			if (INPUT.equals(object.get("type"))) {
				UInteraction composite = interactions.get(object.get("composite").toString());
				UInput input = new UInput(extractId(object), extractModel(object), extractValue(object));
				composite.addInput(input);
			}
		}
	}

	private String extractValue(Map<String, ?> object) {
		return object.get("text").toString();
	}

	public void extractInteractons() {
		for (Map<String, ?> object : elements) {
			if (INTERACTION.equals(object.get("type"))) {
				interactions.put(object.get("id").toString(), build(object));
			}
		}
	}

	private static UInteraction build(Map<String, ?> object) {
		return new UInteraction(extractId(object), extractModel(object));
	}

	private static String extractId(Map<String, ?> object) {
		return object.get("id").toString();
	}

	private static String extractModel(Map<String, ?> object) {
		@SuppressWarnings("unchecked")
		Map<String, ?> userData = (Map<String, ?>) object.get("userData");
		return userData.get("model").toString();
	}

	public UInteraction firstState() {
		return first;
	}

}
