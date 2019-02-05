package net.douglashiura.leb.uid.scenario.glue.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;

import net.douglashiura.us.Interaction;
import net.douglashiura.usuid.project.util.FileScenario;
import net.douglashiura.usuid.project.util.Files;

public class GetAllScenariosOfAPath {
	private List<Interaction> scenarios;

	public GetAllScenariosOfAPath(IContainer directory) throws IOException, CoreException {
		scenarios = new ArrayList<>();
		read(directory);

	}

	private void read(IContainer root) throws IOException, CoreException {
		List<FileScenario> reader = Files.from(root);
		for (FileScenario file : reader) {
			scenarios.addAll(file.getPaths());
		}
	}

	public List<Interaction> getAll() {
		return scenarios;
	}

}
