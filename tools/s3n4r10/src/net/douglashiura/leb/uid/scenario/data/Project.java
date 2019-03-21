package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Project {

	private static File DEFAULT_DIR = null;
	private File directory;

	private Project(File defaultDir, String path) {
		this(new File(defaultDir, path));
	}

	private Project(File defaultDir) {
		directory = defaultDir;
		if (!directory.exists())
			directory.mkdirs();
	}

	public static Project get(String path) throws IOException {
		return get().enter(path);
	}

	public static Project get() {
		return new Project(getDefaultDir());
	}

	private static File getDefaultDir() {
		if (DEFAULT_DIR == null) {
			DEFAULT_DIR = new File(System.getProperty("user.home"), "us-uid");
		}
		return DEFAULT_DIR;
	}

	public Project enter(String path) throws IOException {
		if (directory.isDirectory()) {
			return new Project(directory, path);
		}
		throw new RuntimeException("Não é diretório");
	}

	public List<String> getFolders() {
		List<String> diretoriesName = new LinkedList<String>();
		for (File file : directory.listFiles()) {
			if (file.isDirectory())
				diretoriesName.add(file.getName());
		}
		return diretoriesName;
	}

	public List<String> getScenariesAsNames() throws IOException {
		List<Scenario> scenaries = getScenaries();
		List<String> manyScenaries = new ArrayList<String>(scenaries.size());
		for (Scenario file : scenaries) {
			manyScenaries.add(file.getVirtualName());
		}
		return manyScenaries;
	}

	public List<Scenario> getScenaries() throws IOException {
		List<Scenario> scenarios = new LinkedList<Scenario>();
		for (File file : directory.listFiles(new FilterScenario())) {
			scenarios.add(new Scenario(file, getDefaultDir()));
		}
		return scenarios;
	}

	public Scenario getScenario() {
		return new Scenario(directory, getDefaultDir());
	}

	public Scenario newScenario(String name) throws IOException {
		File file = new File(directory, String.format("%s.us", name));
		Scenario scenario = new Scenario(file, getDefaultDir());
		scenario.create();
		return scenario;
	}
}
