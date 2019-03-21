package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.io.FilenameFilter;

public class FilterScenario implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".us") && new File(dir, name).isFile();
	}

}
