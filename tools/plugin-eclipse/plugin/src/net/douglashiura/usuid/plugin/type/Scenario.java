package net.douglashiura.usuid.plugin.type;

import java.util.ArrayList;
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

	private  List<InteractionGeometry>  firsts;
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
		firsts = extractFirstState();
	}
	public  List<InteractionGeometry>  starts() {
		return firsts;
	}
	private List<InteractionGeometry> extractFirstState() {
		List<InteractionGeometry> firsts= new ArrayList<InteractionGeometry>();
		for (InteractionGeometry aState : interactions.values()) {
			if (nonArrival(aState)) {
				 firsts.add(aState);
			}
		}
		return firsts;
	}

	private boolean nonArrival(InteractionGeometry nonTarget) {
		for (InteractionGeometry aState : interactions.values()) {
			TransactionGeometry transaction = aState.getTransaction();
			if (transaction != null && contains(nonTarget, transaction)) {
				return false;
			}
		}
		return true;
	}

	private Boolean contains(InteractionGeometry nonTarget, TransactionGeometry transaction) {
		for (InteractionGeometry aTransaction : transaction.getTargets()) {
			if (nonTarget.equals(aTransaction)) {
				return true;
			}
		}
		return false;

	}

	private void extractOutputs() {
		for (LinkedTreeMap<String, ?> object : elements) {
			if (OUTPUT.equals(object.get("type"))) {
				InteractionGeometry composite = interactions.get(object.get("composite").toString());
				OutputGeometry output = new OutputGeometry(extractId(object), extractGeometry(object),
						extractFixture(object), extractValue(object));
				composite.addOutput(output);
			}
		}
	}

	class InteractionGeometryId {
		private String id;
		private InteractionGeometry target;

		public InteractionGeometryId(String id, InteractionGeometry target) {
			this.id = id;
			this.target = target;

		}
	}

	@SuppressWarnings("unchecked")
	private void extractTransaction() {
		HashMap<InteractionGeometry, TransactionGeometry> transactions = new HashMap<>();
		for (LinkedTreeMap<String, ?> object : elements) {
			if (TRANSACTION.equals(object.get("type"))) {
				Object source = ((LinkedTreeMap<String, ?>) object.get("source")).get("node");
				Object target = ((LinkedTreeMap<String, ?>) object.get("target")).get("node");
				InteractionGeometry aSource = interactions.get(source);
				InteractionGeometry aTarget = interactions.get(target);
				TransactionGeometry transaction = null;
				if (transactions.containsKey(aSource)) {
					transaction = transactions.get(aSource);
				} else {
					transaction = new TransactionGeometry(extractId(object), aSource);
					transactions.put(aSource, transaction);
					aSource.setTransaction(transaction);
				}
				transaction.addTarget(aTarget);
			}
		}

	}

	private void extractInputs() {
		for (LinkedTreeMap<String, ?> object : elements) {
			if (INPUT.equals(object.get("type"))) {
				InteractionGeometry composite = interactions.get(object.get("composite").toString());
				InputGeometry input = new InputGeometry(extractId(object), extractGeometry(object),
						extractFixture(object), extractValue(object));
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
}
