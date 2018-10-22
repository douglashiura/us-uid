package tests.br.ufsc.leb.adangomes.us.serial;

import static org.junit.Assert.*;

import org.junit.Test;

import net.douglashiura.us.serial.InputFile;

public class InputFileTest {

	@Test
	public void createInput() throws Exception {
		String file = "file_name";
		String content = "content";
		InputFile input = new InputFile(file, content);
		assertEquals(file, input.getFile());
		assertEquals(content, input.getContent());
	}

}
