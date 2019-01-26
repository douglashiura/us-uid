package test.net.douglashiura.leb.uid.sc3n4r10.data;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.data.Project;

public class TesteProjets {

	@Test
	public void empty() throws Exception {
		List<String> folders = Project.get("").getFolders();
		assertTrue(folders.contains("a"));
	}

	@Test
	public void root() throws Exception {
		List<String> folders = Project.get("/").getFolders();
		assertTrue(folders.contains("a"));
	}

	@Test
	public void byDirectory() throws Exception {
		List<String> folders = Project.get("a").getFolders();
		assertTrue(folders.isEmpty());
	}

	@Test
	public void scenarios() throws Exception {
		Project.get("another/us.us");
		List<String> scenarios = Project.get("another").getScenariesAsNames();
		assertTrue(scenarios.contains("us.us"));
		java.io.File file = new java.io.File(System.getProperty("user.home"), "another");
		file.delete();
	}

	@Test
	public void rootWithDirectory() throws Exception {
		List<String> folders = Project.get("/a").getFolders();
		assertTrue(folders.isEmpty());
	}
}
