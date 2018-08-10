package net.douglashiura.us.serial;

import java.io.Serializable;

public class Input implements Serializable {

	private static final long serialVersionUID = 1L;
	private String file;
	private String content;

	public Input(String file, String content) {
		this.file = file;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public String getFile() {
		return file;
	}

}
