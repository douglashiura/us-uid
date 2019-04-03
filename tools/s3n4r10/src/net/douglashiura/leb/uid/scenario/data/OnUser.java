package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.SimpleNameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UserNameNullException;

public class OnUser {

	private File workDirectoryOfUser;

	OnUser(File workDirectoryUser) {
		this.workDirectoryOfUser = workDirectoryUser;
	}

	public Project createProject(SimpleName project) {
		File directory = new File(workDirectoryOfUser, project.getName());
		directory.mkdir();
		return new Project(directory, project);
	}

	public List<Project> listProjects() throws UserNameNullException, SimpleNameEmptyException,
			SimpleNameBiggerThat30Exception, SimpleNameInvalidException {
		List<Project> projects = new ArrayList<Project>();
		for (File directory : workDirectoryOfUser.listFiles()) {
			if (directory.isDirectory()) {
				projects.add(new Project(directory, new SimpleName(directory.getName())));
			}
		}
		return projects;
	}

	public OnProject onProject(SimpleName project) throws ProjectInvalidExeception {
		File file = new File(workDirectoryOfUser, project.getName());
		if (!file.exists()) {
			throw new ProjectInvalidExeception();
		}
		return new OnProject(file);
	}

}
