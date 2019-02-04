package net.douglashiura.usuid.project;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;

import net.douglashiura.usuid.plugin.type.Rateable;
import net.douglashiura.usuid.project.util.FileScenario;

public interface Elementable {
	public void setFixtureName(String text) throws CoreException, IOException;

	public FileScenario getFileScenario();

	public Rateable getElement();

}
