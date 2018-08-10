package net.douglashiura.leb.uid.scenario.glue.code;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.douglashiura.leb.uid.scenario.Interaction;

public class GlueCodeGenerate {

	private GlueCodeOfScenarioSet codeOfScenarioSet;

	public GlueCodeGenerate(String _package, File directory) throws IOException {
		GetAllScenariosOfAPath allScenariosOfAPath = new GetAllScenariosOfAPath(directory);
		List<Interaction> scenaries = ExtractInteractions.from(allScenariosOfAPath.getAll());
		codeOfScenarioSet = new GlueCodeOfScenarioSet(_package, scenaries);

	}

	@Override
	public String toString() {
		List<ClassTemplate> classes = codeOfScenarioSet.getClasses();
		String code = new String();
		for (ClassTemplate classTemplate : classes) {
			code += classTemplate.toString() + "\n";
		}
		return code;
	}

}
