package test.net.douglashiura.sc3n4r10.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.servlet.util.FileUtil;
import net.douglashiura.leb.uid.scenario.servlet.util.NotAFileException;

public class FileUtilTest {

	@Test(expected = NotAFileException.class)
	public void empty() throws Exception {
		new FileUtil("");
	}

	@Test(expected = NotAFileException.class)
	public void whithoutFile() throws Exception {
		new FileUtil("/path");
	}

	@Test(expected = NotAFileException.class)
	public void fileNonUsud() throws Exception {
		new FileUtil("File.im");
	}

	@Test
	public void file() throws Exception {
		FileUtil file = new FileUtil("File.us");
		assertEquals("/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "" }, file.getPathsOfDirectory());
	}

	@Test
	public void fileWithPart() throws Exception {
		FileUtil file = new FileUtil("/File.us");
		assertEquals("/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "" }, file.getPathsOfDirectory());
	}

	@Test
	public void standartWithoutStart() throws Exception {
		FileUtil file = new FileUtil("path/path2/File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
	}

	@Test
	public void standart() throws Exception {
		FileUtil file = new FileUtil("/path/path2/File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
	}
	@Test
	public void standartWithDot() throws Exception {
		FileUtil file = new FileUtil("path.path2.File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
	}
	
	@Test
	public void standartStartWithDot() throws Exception {
		FileUtil file = new FileUtil(".path.path2.File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
	}
	

}
