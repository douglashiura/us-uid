package test.net.douglashiura.sc3n4r10.util;

import static org.junit.Assert.*;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.servlet.util.FileName;
import net.douglashiura.leb.uid.scenario.servlet.util.NotAFileException;

public class FileNameTest {

	@Test(expected = NotAFileException.class)
	public void empty() throws Exception {
		new FileName("");
	}

	@Test(expected = NotAFileException.class)
	public void whithoutFile() throws Exception {
		new FileName("/path");
	}

	@Test()
	public void fileNonUsud() throws Exception {
		new FileName("File.im");
	}

	@Test
	public void file() throws Exception {
		FileName file = new FileName("File.us");
		assertEquals("/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "" }, file.getPathsOfDirectory());
	}

	@Test
	public void fileWithPart() throws Exception {
		FileName file = new FileName("/File.us");
		assertEquals("/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "" }, file.getPathsOfDirectory());
		assertEquals("File.us",file.getNameScenario());
	}

	@Test
	public void standartWithoutStart() throws Exception {
		FileName file = new FileName("path/path2/File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
		assertEquals("path.path2.File.us",file.getNameScenario());
	}
	@Test
	public void standartWithoutExtension() throws Exception {
		FileName file = new FileName("path.path2.File");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
		assertEquals("path.path2.File.us",file.getNameScenario());
	}
	

	@Test
	public void standart() throws Exception {
		FileName file = new FileName("/path/path2/File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
		assertEquals("path.path2.File.us",file.getNameScenario());
	}

	@Test
	public void standartWithDot() throws Exception {
		FileName file = new FileName("path.path2.File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
		assertEquals("path.path2.File.us",file.getNameScenario());
	}

	@Test
	public void standartStartWithDot() throws Exception {
		FileName file = new FileName(".path.path2.File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
		assertEquals("path.path2.File.us",file.getNameScenario());
	}
	
	
	@Test
	public void packageName() throws Exception {
		FileName file = new FileName(".path.path2","File");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
		assertEquals("path.path2.File.us",file.getNameScenario());
	}
	@Test
	public void packageNameWithExtension() throws Exception {
		FileName file = new FileName(".path.path2","File.us");
		assertEquals("/path/path2/", file.getDirectory());
		assertEquals("File.us", file.getName());
		assertEquals("File", file.getNameWithoutExtension());
		assertArrayEquals(new String[] { "path", "path2" }, file.getPathsOfDirectory());
		assertEquals("path.path2.File.us",file.getNameScenario());
	}
	

}
