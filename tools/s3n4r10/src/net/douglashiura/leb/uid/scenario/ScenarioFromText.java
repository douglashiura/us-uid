package net.douglashiura.leb.uid.scenario;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ScenarioFromText {

	private static final Object INTERACTION = "br.ufsc.leb.uid.scenario.Interacao";
	private static final Object OUTPUT = "br.ufsc.leb.uid.scenario.SystemOutput";
	private static final Object INPUT = "br.ufsc.leb.uid.scenario.UserInput";
	private static final Object TRANSACTION = "br.ufsc.leb.uid.scenario.Transaction";
	private HashMap<String, Interaction> interactions;
	private List<Map<String, ?>> elements;
	private Interaction first;
	private String origem;

	@SuppressWarnings("unchecked")
	public ScenarioFromText(String text,String scenarioName) {
		this.origem = scenarioName;
		Gson gson = new Gson();
		this.elements = gson.fromJson(text, List.class);
		this.interactions = new HashMap<String, Interaction>();
		extractInteractons();
		extractInputs();
		extractOutputs();
		extractTransaction();
		first = extractFirstState();
	}

	private Interaction extractFirstState() {
		Collection<Interaction> states = interactions.values();
		for (Interaction aState : states) {
			if (nonArrival(aState)) {
				return aState;
			}
		}
		return null;
	}

	private boolean nonArrival(Interaction nonTarget) {
		for (Interaction aState : interactions.values()) {
			Transaction transaction = aState.getTransaction();
			if (transaction != null && nonTarget.equals(transaction.getTarget())) {
				return false;
			}
		}
		return true;

	}

	private void extractOutputs() {
		for (Map<String, ?> object : elements) {
			if (OUTPUT.equals(object.get("type"))) {
				Interaction composite = interactions.get(object.get("composite").toString());
				Output output = new Output(extractId(object), extractModel(object), extractValue(object));
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
				Interaction aSource = interactions.get(source);
				Interaction aTarget = interactions.get(target);
				Transaction transaction = new Transaction(extractId(object), aSource, aTarget);
				aSource.setTransaction(transaction);
			}
		}
	}

	private void extractInputs() {
		for (Map<String, ?> object : elements) {
			if (INPUT.equals(object.get("type"))) {
				Interaction composite = interactions.get(object.get("composite").toString());
				Input input = new Input(extractId(object), extractModel(object), extractValue(object));
				composite.addInput(input);
			}
		}
	}

	private String extractValue(Map<String, ?> object) {
		return object.get("text").toString();
	}

	private void extractInteractons() {
		try {
			for (Map<String, ?> object : elements) {
				if (INTERACTION.equals(object.get("type"))) {
					interactions.put(object.get("id").toString(), build(object));
				}
			}
		} catch (NullPointerException e) {
			throw new RuntimeException("Erro:"+origem);
		}
	}

	private static Interaction build(Map<String, ?> object) {
		return new Interaction(extractId(object), extractModel(object));
	}

	private static String extractId(Map<String, ?> object) {
		return object.get("id").toString();
	}

	private static String extractModel(Map<String, ?> object) {
		@SuppressWarnings("unchecked")
		Map<String, ?> userData = (Map<String, ?>) object.get("userData");
		return userData.get("model").toString();
	}

	public Interaction firstState() {
		return first;
	}

}
