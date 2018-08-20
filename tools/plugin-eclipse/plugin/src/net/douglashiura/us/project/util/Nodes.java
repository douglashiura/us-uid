package net.douglashiura.us.project.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class Nodes {
	private static final String EXTENSION = "us";
	private List<FileScenario> scenarios;

	private Nodes(IContainer root) throws CoreException {
		scenarios = new ArrayList<>();
		processContainer(root);
	}

	static public List<FileScenario> from(IContainer root) throws CoreException {
		return new Nodes(root).getScenarios();
	}

	public List<FileScenario> getScenarios() {
		return scenarios;
	}

	private void processContainer(IContainer container) throws CoreException {
		IResource[] members = container.members();
		for (IResource member : members) {
			if (member instanceof IContainer) {
				processContainer((IContainer) member);
			} else if (member instanceof IFile) {
				processFile((IFile) member);
			}
		}
	}

	private void processFile(IFile member) {
		if (EXTENSION.equals(member.getFileExtension())) {
			scenarios.add(new FileScenario(member));
		}
	}
}