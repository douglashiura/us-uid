package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.servlet.util.FileUtil;

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

	public List<String> getScenariosAsNames() throws IOException {
		List<Scenario> scenarios = getScenarios();
		List<String> manyScenarios = new ArrayList<String>(scenarios.size());
		for (Scenario file : scenarios) {
			manyScenarios.add(file.getVirtualName());
		}
		return manyScenarios;
	}

	public List<Scenario> getScenarios() throws IOException {
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

	public void delete() {
		directory.delete();
	}

	public void rename(FileUtil file) throws IOException {
		byte[] data = getScenario().getDocument().getBytes();
		delete();
		Project.get(file.getDirectory()).newScenario(file.getNameWithoutExtension()).write(data);
	}

	public void clone(FileUtil file) throws IOException {
		byte[] data = getScenario().getDocument().getBytes();
		Project.get(file.getDirectory()).newScenario(file.getNameWithoutExtension()).write(data);
	}
}
