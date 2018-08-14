package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Folder {
	private File dir;
	private Integer deep;
	private String name;

	public Folder(File dir) {
		this.dir = dir;
		this.deep = 0;
		this.name = File.separator;
	}

	public static Folder getDefault() {
		File defaultDir = new File(System.getProperty("user.home"), "us-uid");
		if (!defaultDir.exists())
			defaultDir.mkdir();
		return new Folder(defaultDir);
	}

	public Folder(File newDir, Integer deep) {
		this.dir = newDir;
		this.deep = deep;
		this.name = newDir.getName();
	}

	public Folder make(String[] directories) {
		File aFile = dir;
		Integer aDeep = deep;

		for (String directory : directories) {
			aFile = new File(aFile, directory);
			aDeep = aDeep + 1;
			if (!aFile.exists())
				aFile.mkdir();
		}
		Folder aFolder = new Folder(aFile, aDeep);
		return aFolder;
	}

	public List<Scenario> getScenaries() {
		File[] files = dir.listFiles(new FilterScenario());
		List<Scenario> scenarios = new ArrayList<Scenario>();
		for (File file : files) {
			scenarios.add(new Scenario(file,null));
		}
		return scenarios;
	}

	public List<Folder> getFolders() {
		File[] dirs = dir.listFiles();
		ArrayList<Folder> folders = new ArrayList<Folder>();
		Integer oneDeep = deep + 1;
		for (File file : dirs) {
			if (file.isDirectory()) {
				folders.add(new Folder(file, oneDeep));
			}
		}
		return folders;

	}

	public Integer getDeep() {
		return deep;
	}

	public String getName() {
		return name;
	}

	public void newScenario(String name) throws IOException {
		newScenario(name, "");

	}

	public void newScenario(String name, String conteudo) throws IOException {
		File newFile = new File(dir, name + ".us");
		new Scenario(newFile,null).write(conteudo.getBytes(Charset.forName("UTF-8")));
		
	}

}
