package net.douglashiura.scenario.plugin.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import net.douglashiura.scenario.plugin.editor.Executor;
import net.douglashiura.scenario.plugin.editor.PaintScenario;
import net.douglashiura.scenario.plugin.editor.ScenarioView;
import net.douglashiura.scenario.plugin.type.Rateable;
import net.douglashiura.scenario.project.util.FileScenario;
import net.douglashiura.scenario.project.util.Files;
import net.douglashiura.us.serial.Results;

public class Runner {

	private static Runner action;
	private Executor executor;
	private PaintScenario paintScenario;
	private FileScenario current;
	protected ViewTests viewTests;
	private IContainer container;
	private ScenarioView scenario;

	private Runner() {
	}

	public static Runner getRunner() {
		if (action == null)
			action = new Runner();
		return action;
	}

	public void setContainer(IContainer container) {
		this.container = container;
	}

	public void run() {
		execute(Arrays.asList(current), current.getProject());
	}

	public void runAll() throws CoreException, IOException {
		List<FileScenario> nodes = Files.from(container);
		for (FileScenario fileScenario : nodes) {
			fileScenario.prepareToExecute();
		}
		execute(nodes, container.getProject());
	}

	private void execute(List<FileScenario> scenarios, IProject project) {
		if (current != null) {
			current.prepareToExecute();
		}
		try {
			viewTests.popularArvore(scenarios);
			executor = new Executor(viewTests.getViewSite().getShell(), scenarios);
			viewTests.setExecutionAmounts(executor);
			executor.execute(project);
		} catch (Exception e) {
			MessageDialog.openInformation(null, "Fault",
					"It is necessary to open before the scenario view and test view! (Window->Show View->Other->Scenario)");
		}
	}

	public boolean getExecution() {
		return executor.getExecution();
	}

	public void addPaintScenario(PaintScenario paintScenario) {
		removePaintScenario();
		this.paintScenario = paintScenario;
		if (scenario != null && !scenario.getCanvas().isDisposed()) {
			scenario.getCanvas().addPaintListener(paintScenario);
			scenario.getCanvas().redraw();
		} else {
			MessageDialog.openInformation(null, "Fault", "It is necessary to open the scenario view!");
		}
	}

	private void removePaintScenario() {
		if (scenario != null && paintScenario != null) {
			scenario.getCanvas().removePaintListener(paintScenario);
		}
	}

	public void setCanvas(ScenarioView canvas) {
		this.scenario = canvas;
	}

	public void setCurrent(FileScenario scenario) {
		this.current = scenario;
		try {
			removePaintScenario();
			addPaintScenario(new PaintScenario(current.getElements()));
		} catch (NullPointerException error) {
			MessageDialog.openInformation(null, "Fault", "Could not open file");
		}
	}

	public FileScenario getCurrent() {
		return this.current;
	}

	public void redraw(Rateable rateable) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				scenario.getCanvas().redraw();
				if (rateable != null)
					setOrigin(rateable.getGeometry().getX(), rateable.getGeometry().getY());
			}
		});
	}

	public void updateStatusExecution(Results status) {
		executor.updateStatusExecution(status);
		viewTests.setExecutionAmounts(executor);
	}

	public void setViewTests(ViewTests viewTests) {
		this.viewTests = viewTests;
	}

	public void setOrigin(Integer x, Integer y) {
		if (scenario != null) {
			scenario.setOrigin(x, y);
		}
	}

}