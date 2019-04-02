package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.douglashiura.leb.uid.scenario.servlet.util.FileName;
import net.douglashiura.leb.uid.scenario.servlet.util.NotAFileException;

public class Scenario {

	private File file;
	private File defaultDir;

	public Scenario(File file, File defaultDir) {
		this.file = file;
		this.defaultDir = defaultDir;
	}

	public String getDocument() throws IOException {
		FileInputStream stream = new FileInputStream(file);
		byte[] b = new byte[stream.available()];
		stream.read(b);
		stream.close();
		return new String(b);
	}

	public void write(byte[] data) throws IOException {
		FileOutputStream stream = new FileOutputStream(file);
		stream.write(data);
		stream.close();
	}

	public String getVirtualName() throws NotAFileException {
		return new FileName(file.getAbsolutePath().replace(defaultDir.getAbsolutePath(), "")).getNameScenario();
	}

	public void create() throws IOException {
		file.createNewFile();
		write("".getBytes());
	}
}
