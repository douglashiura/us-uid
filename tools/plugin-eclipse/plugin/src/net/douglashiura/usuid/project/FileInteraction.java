package net.douglashiura.usuid.project;

import org.eclipse.core.runtime.CoreException;

import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.project.util.FileScenario;

public class FileInteraction implements SetFixtureName {

	private FileScenario fileScenario;
	private InteractionGeometry interaction;

	public FileInteraction(FileScenario fileScenario, InteractionGeometry interaction) {
		this.fileScenario = fileScenario;
		this.interaction = interaction;
	}

	public String getFile() {
		return fileScenario.getName();
	}

	public String getFixtureName() {
		return interaction.getFixtureName();
	}

	public String getInputs() {
		return interaction.getInputs().toString();
	}

	public String getOutputs() {
		return interaction.getOutputs().toString();
	}

	@Override
	public void setFixtureName(String text) throws CoreException {
		interaction.setFixtureName(text);
		fileScenario.save();
	}

}
