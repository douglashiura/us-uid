package test.net.douglashiura.sc3n4r10.data.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.douglashiura.leb.uid.scenario.data.ProjectInvalidExeception;
import net.douglashiura.leb.uid.scenario.data.OnProject;
import net.douglashiura.leb.uid.scenario.data.OnUser;
import net.douglashiura.leb.uid.scenario.data.ProjectScenario;
import net.douglashiura.leb.uid.scenario.data.Scenario;
import net.douglashiura.leb.uid.scenario.data.User;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.servlet.util.FileName;

public class OnProjectTest {

	private User douglas;

	@Before
	public void setUp() {
		File defaultDir = new File(System.getProperty("user.home"), "us-uid");
		ProjectScenarioTest.deleteRecursive(defaultDir);
	}

	@Test
	public void onUserEmpty() throws Exception {
		ProjectScenario scenario = new ProjectScenario();
		scenario.createUser(douglas);
		OnUser onUser = scenario.onUser(douglas);
		assertTrue(onUser.listProjects().isEmpty());
	}

	@Test
	public void onUser() throws Exception {
		ProjectScenario scenario = new ProjectScenario();
		scenario.createUser(douglas);
		OnUser onUser = scenario.onUser(douglas);
		SimpleName project = new SimpleName("test");
		onUser.createProject(project);
		File douglasDir = new File(scenario.getWorkDirectory(), douglas.getUsername().getName());
		assertEquals(1, onUser.listProjects().size());
		assertEquals(project, onUser.listProjects().get(0).getName());
		assertTrue(new File(douglasDir, project.getName()).exists());
	}

	@Test
	public void createAProjectWhitoutScenarios() throws Exception {
		ProjectScenario scenario = new ProjectScenario();
		scenario.createUser(douglas);
		OnUser onUser = scenario.onUser(douglas);
		SimpleName project = new SimpleName("test");
		onUser.createProject(project);
		OnProject testProject = onUser.onProject(project);
		assertEquals(0, testProject.listScenarios().size());
		assertEquals(0, testProject.listScenariosAsNames().size());
	}

	@Test
	public void createAScenarios() throws Exception {
		ProjectScenario scenario = new ProjectScenario();
		scenario.createUser(douglas);
		OnUser onUser = scenario.onUser(douglas);
		SimpleName project = new SimpleName("test");
		onUser.createProject(project);
		OnProject testProject = onUser.onProject(project);
		testProject.createNewScenario(new FileName("br.net.Test.us"));
		List<Scenario> listScenarios = testProject.listScenarios();
		assertEquals(1, listScenarios.size());
		assertEquals(1, testProject.listScenariosAsNames().size());
		assertEquals("", listScenarios.get(0).getDocument());
		assertEquals("br.net.Test.us", listScenarios.get(0).getVirtualName());
		assertEquals("br.net.Test.us", testProject.listScenariosAsNames().get(0));

	}

	@Test(expected = ProjectInvalidExeception.class)
	public void onProjectWithoutProject() throws Exception {
		ProjectScenario scenario = new ProjectScenario();
		scenario.createUser(douglas);
		OnUser onUser = scenario.onUser(douglas);
		SimpleName project = new SimpleName("test");
		onUser.onProject(project);
	}

}
