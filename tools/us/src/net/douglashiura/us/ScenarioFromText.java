package net.douglashiura.us;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;

import net.douglashiura.us.run.InputRunner;
import net.douglashiura.us.run.InteractionRunner;
import net.douglashiura.us.run.OutputRunner;
import net.douglashiura.us.run.TransactionRunner;

public class ScenarioFromText {

	private static final Object INTERACTION = "br.ufsc.leb.uid.scenario.Interacao";
	private static final Object OUTPUT = "br.ufsc.leb.uid.scenario.SystemOutput";
	private static final Object INPUT = "br.ufsc.leb.uid.scenario.UserInput";
	private static final Object TRANSACTION = "br.ufsc.leb.uid.scenario.Transaction";
	private HashMap<String, InteractionRunner> interactions;
	private List<Map<String, ?>> elements;
	private InteractionRunner first;

	@SuppressWarnings("unchecked")
	public ScenarioFromText(String text) {
		Gson gson = new Gson();
		this.elements = gson.fromJson(text, List.class);
		this.interactions = new HashMap<String, InteractionRunner>();
		extractInteractions();
		extractInputs();
		extractOutputs();
		extractTransaction();
		first = extractFirstState();
	}

	private InteractionRunner extractFirstState() {
		Collection<InteractionRunner> states = interactions.values();
		for (InteractionRunner state : states) {
			if (nonArrival(state)) {
				return state;
			}
		}
		return null;
	}

	private boolean nonArrival(InteractionRunner nonTarget) {
		for (InteractionRunner state : interactions.values()) {
			TransactionRunner transaction = state.getTransaction();
			if (transaction != null && nonTarget.equals(transaction.getTarget())) {
				return false;
			}
		}
		return true;
	}

	private void extractOutputs() {
		for (Map<String, ?> object : elements) {
			if (OUTPUT.equals(object.get("type"))) {
				InteractionRunner composite = interactions.get(object.get("composite").toString());
				OutputRunner output = new OutputRunner(extractId(object), extractModel(object), extractValue(object));
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
				InteractionRunner aSource = interactions.get(source);
				InteractionRunner aTarget = interactions.get(target);
				TransactionRunner transaction = new TransactionRunner(extractId(object), aSource, aTarget);
				aSource.setTransaction(transaction);
			}
		}
	}

	private void extractInputs() {
		for (Map<String, ?> object : elements) {
			if (INPUT.equals(object.get("type"))) {
				InteractionRunner composite = interactions.get(object.get("composite").toString());
				InputRunner input = new InputRunner(extractId(object), extractModel(object), extractValue(object));
				composite.addInput(input);
			}
		}
	}

	private String extractValue(Map<String, ?> object) {
		return object.get("text").toString();
	}

	public void extractInteractions() {
		for (Map<String, ?> object : elements) {
			if (INTERACTION.equals(object.get("type"))) {
				interactions.put(object.get("id").toString(), build(object));
			}
		}
	}

	private static InteractionRunner build(Map<String, ?> object) {
		return new InteractionRunner(extractId(object), extractModel(object));
	}

	private static UUID extractId(Map<String, ?> object) {
		return UUID.fromString(object.get("id").toString());
	}

	private static String extractModel(Map<String, ?> object) {
		@SuppressWarnings("unchecked")
		Map<String, ?> userData = (Map<String, ?>) object.get("userData");
		return userData.get("model").toString();
	}

	public InteractionRunner firstState() {
		return first;
	}

}
