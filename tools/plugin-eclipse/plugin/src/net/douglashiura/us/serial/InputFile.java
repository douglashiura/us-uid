package net.douglashiura.us.serial;

import java.io.Serializable;

public class InputFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private String file;
	private String content;

	public InputFile(String file, String content) {
		this.file = file;
		this.content = content;
	}

	public String getFile() {
		return file;
	}

	public String getContent() {
		return content;
	}

}
