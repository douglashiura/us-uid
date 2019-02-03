package net.douglashiura.usuid.project;

import org.eclipse.core.runtime.CoreException;

import net.douglashiura.usuid.plugin.type.AbstractType;
import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.project.util.FileScenario;

public class Element implements SetFixtureName {

	private FileScenario fileScenario;
	private InteractionGeometry interaction;
	private AbstractType element;

	public Element(FileScenario fileScenario, InteractionGeometry interaction, AbstractType element) {
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
	public void setFixtureName(String text) throws CoreException {
		element.setFixtureName(text);
		fileScenario.save();
	}

}
