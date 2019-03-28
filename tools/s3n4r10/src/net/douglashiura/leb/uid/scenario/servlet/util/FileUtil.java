package net.douglashiura.leb.uid.scenario.servlet.util;

import java.io.File;

import net.douglashiura.leb.uid.scenario.data.FilterScenario;

public class FileUtil {

	private String name;
	private String directory;

	public FileUtil(String path) throws ExceptionNotAFile {
		name = extractFile(path);
		directory = extractDiretory(path);
	}

	public FileUtil(java.net.URI uri) throws ExceptionNotAFile {
		this(uri.getPath());
	}

	private String extractFile(String path) throws ExceptionNotAFile {
		if (path.endsWith(FilterScenario.EXTENSION)) {
			return path.substring(path.lastIndexOf(File.separator));
		} else {
			throw new ExceptionNotAFile("Not a file");
		}
	}

	private String extractDiretory(String path) throws ExceptionNotAFile {
		return path.replace(name, "").trim();
	}

	public String getName() {
		return name;
	}

	public String getDirectory() {
		return directory;
	}

	public String[] getPathsOfDirectory() {
		return directory.split(File.separator);
	}

	public String getNameWithoutExtension() {
		return name.replace(FilterScenario.EXTENSION, "");
	}

}
