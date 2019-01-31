package net.douglashiura.us.project.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import net.douglashiura.us.Interaction;
import net.douglashiura.us.project.Elements;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;
import net.douglashiura.usuid.plugin.type.ExtractPahts;
import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.plugin.type.Rateable;
import net.douglashiura.usuid.plugin.type.Scenario;
import net.douglashiura.usuid.plugin.view.Runner;

public class FileScenario {

	private IFile member;
	private List<Result> results;
	private Map<UUID, Rateable> elements = null;
	private String text;
	private Notificable notificable;
	private List<Interaction> paths;

	public FileScenario(IFile member) throws IOException, CoreException {
		this.member = member;
		this.results = new ArrayList<>();
		prepareScenario();
	}

	public String getName() {
		return member.getProjectRelativePath().toString();
	}

	private void prepareScenario() throws IOException, CoreException {
		text = read();
		Scenario scenario = new Scenario(text);
		List<InteractionGeometry> starts = scenario.starts();
		paths = new ExtractPahts(starts).pathsOfExecution();
		elements = Elements.from(starts);
	}

	private String read() throws IOException, CoreException {
		InputStream input = member.getContents();
		byte[] bytes = new byte[input.available()];
		input.read(bytes);
		input.close();
		return new String(bytes);
	}

	public IProject getProject() {
		return member.getProject();
	}

	@Override
	public String toString() {
		try {
			prepareScenario();
			return text;
		} catch (IOException | CoreException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void addResult(Result result) {
		if (!Results.END.equals(result.getResult())) {
			results.add(result);
			Rateable element = elements.get(result.getUuid());
			if (element != null) {
				element.rate(result.getResult().getColor());
				notificationToView();
				if (notificable != null) {
					notificable.addResult(result, element);
				}
			}
		}
		if (Results.isExecutionFinishy(result.getResult())) {
			Results status = generalResult();
			notificable.finishyATestExecution(status);
			Runner.getRunner().updateStatusExecution(status);
		}
	}

	private Results generalResult() {
		for (Result result : results) {
			if (Results.FAIL.equals(result.getResult()))
				return Results.FAIL;
		}
		for (Result result : results) {
			if (Results.ERROR.equals(result.getResult()))
				return Results.ERROR;
		}
		return Results.OK;
	}

	private void notificationToView() {
		if (Runner.getRunner().getCurrent() == this) {
			Runner.getRunner().redraw();
		}
	}

	public void prepareToExecute() {
		if (elements != null) {
			Collection<Rateable> collection = elements.values();
			for (Rateable rateable : collection) {
				rateable.rate(Results.UN_EXECUTED.getColor());
			}
			results.clear();
			notificationToView();
		}
	}

	public void setNotificable(Notificable itemScenario) {
		this.notificable = itemScenario;
	}

	public Collection<Rateable> getElements() throws IOException, CoreException {
		return elements.values();
	}

	public List<Interaction> getPaths() {
		return paths;
	}

}
