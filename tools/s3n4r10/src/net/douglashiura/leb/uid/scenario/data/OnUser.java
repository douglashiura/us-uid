package net.douglashiura.leb.uid.scenario.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameNullException;

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

	public List<Project> listProjects() throws UsernameNullException, UsernameEmptyException,
			UsernameBiggerThat30Exception, UsernameInvalidException {
		List<Project> projects = new ArrayList<Project>();
		for (File directory : workDirectoryOfUser.listFiles()) {
			if (directory.isDirectory()) {
				projects.add(new Project(directory, new SimpleName(directory.getName())));
			}
		}
		return projects;
	}

	public OnProject onProject(SimpleName project) throws InvalidProjectExeception {
		File file = new File(workDirectoryOfUser, project.getName());
		if (!file.exists()) {
			throw new InvalidProjectExeception();
		}
		return new OnProject(file);
	}

}
