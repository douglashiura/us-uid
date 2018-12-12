package net.douglashiura.leb.uid.scenario.glue.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.ScenarioFromText;
import net.douglashiura.leb.uid.scenario.data.FilterScenario;
import net.douglashiura.us.Interaction;

public class GetAllScenariosOfAPath {
	private List<Interaction> scenarios;

	public GetAllScenariosOfAPath(File directory) throws IOException {
		scenarios = new ArrayList<>();
		read(directory);

	}

	private void read(File directory) throws FileNotFoundException, IOException {
		for (File file : directory.listFiles(new FilterScenario())) {
			ScenarioFromText reader = readAScenario(file);
			scenarios.add(reader.firstState());
		}
		File[] dirs = directory.listFiles();
		for (File file : dirs)
			if (file.isDirectory())
				read(file);
	}

	private ScenarioFromText readAScenario(File file) throws FileNotFoundException, IOException {
		FileInputStream text = new FileInputStream(file);
		byte[] b = new byte[text.available()];
		text.read(b);
		text.close();
		ScenarioFromText reader = new ScenarioFromText(new String(b), file.getAbsolutePath());
		return reader;
	}

	public List<Interaction> getAll() {
		return scenarios;
	}

}
