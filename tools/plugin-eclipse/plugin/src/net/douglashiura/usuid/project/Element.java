package net.douglashiura.usuid.project;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;

import net.douglashiura.usuid.plugin.type.AbstractTypeGeometry;
import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.project.util.FileScenario;

public class Element implements Elementable {

	private FileScenario fileScenario;
	private InteractionGeometry interaction;
	private AbstractTypeGeometry element;

	public Element(FileScenario fileScenario, InteractionGeometry interaction, AbstractTypeGeometry element) {
		this.fileScenario = fileScenario;
		this.interaction = interaction;
		this.element = element;
	}

	public String getFile() {
		return fileScenario.getName();
	}

	public String getInteractionFixtureName() {
		return interaction.getFixtureName();
	}

	public String getType() {
		return element.getSimpleType();
	}

	public String getFixtureName() {
		return element.getFixtureName();
	}

	public String getValue() {
		return element.getValue();
	}

	@Override
	public void setFixtureName(String text) throws CoreException, IOException {
		element.setFixtureName(text);
		fileScenario.save();
	}

	public FileScenario getFileScenario() {
		return fileScenario;
	}

	public AbstractTypeGeometry getElement() {
		return element;
	}

}
