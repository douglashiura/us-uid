package net.douglashiura.leb.uid.scenario.servlet.util;

import java.io.File;

import net.douglashiura.leb.uid.scenario.data.FilterScenario;

public class FileUtil {

	private String name;
	private String directory;

	public FileUtil(String path) throws NotAFileException {
		path = path.trim();
		if (path.endsWith(FilterScenario.EXTENSION)) {
			path = path.substring(0, path.length() - FilterScenario.EXTENSION.length());
		} else {
			throw new NotAFileException("Not a US file");
		}
		name = extractFile(path);
		path = path.substring(0, path.length() - name.length());
		if (path.contains(".")) {
			path = path.replaceAll("\\.", File.separator);
		}
		if (!path.startsWith(File.separator)) {
			path = File.separator + path;
		}
		directory = path.trim();
	}

	public FileUtil(java.net.URI uri) throws NotAFileException {
		this(uri.getPath());
	}

	private String extractFile(String path) throws NotAFileException {
		if (path.contains(File.separator)) {
			return path.substring(path.lastIndexOf(File.separator)+1);
		} else if (path.contains(".")) {
			return path.substring(path.lastIndexOf(".")+1);

		} else {
			return path;
		}
	}

	public String getName() {
		return name + FilterScenario.EXTENSION;
	}

	public String getDirectory() {
		return directory;
	}

	public String[] getPathsOfDirectory() {
		if (directory.startsWith("/")) {
			return directory.substring(1).split(File.separator);
		} else {
			return directory.split(File.separator);
		}
	}

	public String getNameWithoutExtension() {
		return name;
	}

}
