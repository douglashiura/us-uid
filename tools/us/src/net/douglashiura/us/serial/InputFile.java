package net.douglashiura.us.serial;

import java.io.Serializable;

public class InputFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private String file;
	private Interaction scenario;

	public InputFile(String file, Interaction scenario) {
		this.file = file;
		this.scenario = scenario;
	}

	public String getFile() {
		return file;
	}

	public Interaction getScenario() {
		return scenario;
	}

}
