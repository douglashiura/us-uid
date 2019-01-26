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
	private HashMap<String, InteractionGeometry> interactions;

	private InteractionGeometry first;
	private List<LinkedTreeMap<String, ?>> elements;

	@SuppressWarnings("unchecked")
	public Scenario(String jsonText) {
		if (jsonText == null || jsonText.equals(""))
			jsonText = "[]";
		this.elements = new GsonBuilder().create().fromJson(jsonText, List.class);
		this.interactions = new HashMap<String, InteractionGeometry>();
		extractInteractons();
		extractInputs();
		extractOutputs();
		extractTransaction();
		first = extractFirstState();
	}

	private InteractionGeometry extractFirstState() {
		Collection<InteractionGeometry> states = interactions.values();
		for (InteractionGeometry aState : states) {
			if (nonArrival(aState)) {
				return aState;
			}
		}
		return null;
	}

	private boolean nonArrival(InteractionGeometry nonTarget) {
		for (InteractionGeometry aState : interactions.values()) {
			TransactionGeometry transaction = aState.getTransaction();
			if (transaction != null && nonTarget.equals(transaction.getTarget())) {
				return false;
			}
		}
		return true;
	}

	private void extractOutputs() {
		for (LinkedTreeMap<String, ?> object : elements) {
			if (OUTPUT.equals(object.get("type"))) {
				InteractionGeometry composite = interactions.get(object.get("composite").toString());
				OutputGeometry output = new OutputGeometry(extractId(object), extractGeometry(object), extractFixture(object),
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
				InteractionGeometry aSource = interactions.get(source);
				InteractionGeometry aTarget = interactions.get(target);
				TransactionGeometry transaction = new TransactionGeometry(extractId(object), aSource, aTarget);
				aSource.setTransaction(transaction);
			}
		}
	}

	private void extractInputs() {
		for (LinkedTreeMap<String, ?> object : elements) {
			if (INPUT.equals(object.get("type"))) {
				InteractionGeometry composite = interactions.get(object.get("composite").toString());
				InputGeometry input = new InputGeometry(extractId(object), extractGeometry(object), extractFixture(object),
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

	private static InteractionGeometry build(LinkedTreeMap<String, ?> object) {
		return new InteractionGeometry(extractId(object), extractGeometry(object), extractFixture(object));
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

	public InteractionGeometry firstState() {
		return first;
	}

}
