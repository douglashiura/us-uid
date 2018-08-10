package net.douglashiura.leb.uid.scenario.glue.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.douglashiura.leb.uid.scenario.Input;
import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.Output;

public class ClassTemplate {

	private CharSequence _package;
	private List<Interaction> interactions;

	public ClassTemplate(String _package, List<Interaction> interactions) {
		this._package = _package;
		this.interactions = interactions;
	}

	@Override
	public String toString() {
		String klassTemplate = "package test.{PACKAGE};" 
							+ "\nimport net.douglashiura.us.Fixture;"
							+ "\n@Fixture(\"{FIXTURE_NAME}\")" 
							+ "\npublic class Fixture{FIXTURE_NAME} " 
							+ "{" 
							+ "{METHODS}"
							+ "\n}";

		return klassTemplate.replace("{PACKAGE}", _package)
				.replace("{FIXTURE_NAME}", interactions.get(0).getFixture())
				.replace("{METHODS}", mountMethods());
	}

	private CharSequence mountMethods() {
		return inputsMethods() + outputMethods() + transactionMethods();
	}

	private CharSequence transactionMethods() {
		String template = "	public void to{FIXTURE_NAME}(){\n	}";
		String methods = "";
		Set<String> methodsList = new HashSet<>();
		for (Interaction interaction : interactions) {
			if (interaction.getTransaction() != null) {
				methodsList.add(interaction.getTransaction().getTarget().getFixture());
			}
		}
		for (String string : methodsList) {
			CharSequence camelCase = string.substring(0, 1).toUpperCase() + string.substring(1);
			methods += "\n" + template.replace("{FIXTURE_NAME}", camelCase);
		}
		return methods;
	}

	private String outputMethods() {
		String template = "	public String get{FIXTURE_NAME}(){\n		return null;\n	}";
		String methods = "";
		Set<String> methodsList = new HashSet<>();
		for (Interaction interaction : interactions) {
			for (Output output : interaction.getOutputs()) {
				methodsList.add(output.getFixture());
			}
		}
		for (String string : emOrdem(methodsList)) {
			CharSequence camelCase = string.substring(0, 1).toUpperCase() + string.substring(1);
			methods += "\n" + template.replace("{FIXTURE_NAME}", camelCase);
		}
		return methods;
	}

	private List<String> emOrdem(Set<String> methodsList) {
		List<String> list = new ArrayList<>(methodsList);
		Collections.sort(list);
		return list;
	}

	private CharSequence inputsMethods() {
		String template = "	public void set{FIXTURE_NAME}(String value){\n	}";
		String methods = "";
		Set<String> methodsList = new HashSet<>();
		for (Interaction interaction : interactions) {
			for (Input input : interaction.getInputs()) {
				methodsList.add(input.getFixture());
			}
		}
		for (String string : emOrdem(methodsList)) {
			CharSequence camelCase = string.substring(0, 1).toUpperCase() + string.substring(1);
			methods += "\n" + template.replace("{FIXTURE_NAME}", camelCase);
		}
		return methods;
	}

}