package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.io.FilenameFilter;

public class FilterScenario implements FilenameFilter {

	public static final String EXTENSION = ".us";

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(EXTENSION) && new File(dir, name).isFile();
	}

}
