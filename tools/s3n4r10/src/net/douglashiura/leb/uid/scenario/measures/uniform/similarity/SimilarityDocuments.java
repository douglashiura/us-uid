package net.douglashiura.leb.uid.scenario.measures.uniform.similarity;

import java.io.IOException;

import org.custommonkey.xmlunit.Diff;
import org.xml.sax.SAXException;

import net.douglashiura.leb.uid.scenario.Input;
import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.Output;

public class SimilarityDocuments {

	private Diff myDiff;

	public SimilarityDocuments(Interaction interactionA, Interaction interactionB) throws SAXException, IOException {
		String a = create(interactionA);
		String b = create(interactionB);
		 myDiff = new Diff(a, b);
	}

	private String create(Interaction interaction) {
		return "<document>"+createOfInterection(interaction)+"</document>";
	}

	private String createOfInterection(Interaction interaction) {
		String state = "<interaction>" + inputs(interaction) + outputs(interaction) + "</interaction>";
		if (interaction.getTransaction() != null)
			return state + createOfInterection(interaction.getTransaction().getTarget());
		return state;
	}

	private String outputs(Interaction interaction) {
		String outputs = "";
		for (Output output : interaction.getOutputs()) {
			outputs += "<output><String>" + output.getValue().replace("<", "-").replace(">", "-") + "</String></output>";
		}
		return "<outputs>" + outputs + "</outputs>";
	}

	private String inputs(Interaction interaction) {
		String inputs = "";
		for (Input input : interaction.getInputs()) {
			inputs += "<input><String>" + input.getValue().replace("<", "-").replace(">", "-") + "</String></input>";
		}
		return "<inputs>" + inputs + "</inputs>";
	}

	public Boolean getSimilarity() {
		return myDiff.similar();
	}

	public Boolean getIdentical() {
		return myDiff.identical();
	}

}
