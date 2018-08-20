package net.douglashiura.usuid.plugin.type;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

public class Scenario {

	private static final Object INTERACTION = "br.ufsc.leb.uid.scenario.Interacao";
	private static final Object OUTPUT = "br.ufsc.leb.uid.scenario.SystemOutput";
	private static final Object INPUT = "br.ufsc.leb.uid.scenario.UserInput";
	private static final Object TRANSACTION = "br.ufsc.leb.uid.scenario.Transaction";
	private HashMap<String, Interaction> interactions;

	private Interaction first;
	private List<LinkedTreeMap<String, ?>> elements;

	@SuppressWarnings("unchecked")
	public Scenario(String jsonText) {
		if (jsonText == null || jsonText.equals(""))
			jsonText = "[]";
		this.elements = new GsonBuilder().create().fromJson(jsonText, List.class);
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
		for (LinkedTreeMap<String, ?> object : elements) {
			if (OUTPUT.equals(object.get("type"))) {
				Interaction composite = interactions.get(object.get("composite").toString());
				Output output = new Output(extractId(object), extractGeometry(object), extractFixture(object),
						extractValue(object));
				composite.addOutput(output);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void extractTransaction() {
		for (LinkedTreeMap<String, ?> object : elements) {
			if (TRANSACTION.equals(object.get("type"))) {
				Object source = ((LinkedTreeMap<String, ?>) object.get("source")).get("node");
				Object target = ((LinkedTreeMap<String, ?>) object.get("target")).get("node");
				Interaction aSource = interactions.get(source);
				Interaction aTarget = interactions.get(target);
				Transaction transaction = new Transaction(extractId(object), aSource, aTarget);
				aSource.setTransaction(transaction);
			}
		}
	}

	private void extractInputs() {
		for (LinkedTreeMap<String, ?> object : elements) {
			if (INPUT.equals(object.get("type"))) {
				Interaction composite = interactions.get(object.get("composite").toString());
				Input input = new Input(extractId(object), extractGeometry(object), extractFixture(object),
						extractValue(object));
				composite.addInput(input);
			}
		}
	}

	private String extractValue(LinkedTreeMap<String, ?> object) {
		return object.get("text").toString();
	}

	public void extractInteractons() {
		for (LinkedTreeMap<String, ?> object : elements) {
			if (INTERACTION.equals(object.get("type"))) {
				interactions.put(object.get("id").toString(), build(object));
			}
		}
	}

	private static Interaction build(LinkedTreeMap<String, ?> object) {
		return new Interaction(extractId(object), extractGeometry(object), extractFixture(object));
	}

	private static String extractId(LinkedTreeMap<String, ?> object) {
		return object.get("id").toString();
	}

	private static String extractFixture(LinkedTreeMap<String, ?> object) {
		@SuppressWarnings("unchecked")
		LinkedTreeMap<String, ?> userData = (LinkedTreeMap<String, ?>) object.get("userData");
		return userData.get("model").toString();
	}

	private static Geometry extractGeometry(LinkedTreeMap<String, ?> object) {
		Integer x = Float.valueOf(object.get("x").toString()).intValue();
		Integer y = Float.valueOf(object.get("y").toString()).intValue();
		Integer width = Float.valueOf(object.get("width").toString()).intValue();
		Integer hieght = Float.valueOf(object.get("height").toString()).intValue();
		Geometry geometria = new Geometry(x, y, width, hieght);
		return geometria;
	}

	public Interaction firstState() {
		return first;
	}

}
