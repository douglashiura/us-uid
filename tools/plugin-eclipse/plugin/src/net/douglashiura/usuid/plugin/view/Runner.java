package net.douglashiura.usuid.plugin.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

import net.douglashiura.us.serial.Results;
import net.douglashiura.usuid.plugin.editor.Executor;
import net.douglashiura.usuid.plugin.editor.PaintScenario;
import net.douglashiura.usuid.project.util.FileScenario;
import net.douglashiura.usuid.project.util.Nodes;

public class Runner {

	private static Runner action;
	private Canvas canvas;
	private Executor executor;
	private PaintScenario paintScenario;
	private FileScenario current;
	protected ViewTests viewTests;
	private IContainer container;

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
		current.prepareToExecute();
		execute(Arrays.asList(current), current.getProject());
	}

	public void runAll() throws CoreException, IOException {
		List<FileScenario> nodes = Nodes.from(container);
		for (FileScenario fileScenario : nodes) {
			fileScenario.prepareToExecute();
		}
		execute(nodes, container.getProject());

	}

	private void execute(List<FileScenario> scenarios, IProject project) {
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
		if (canvas != null && !canvas.isDisposed()) {
			canvas.addPaintListener(paintScenario);
			canvas.redraw();
		} else {
			MessageDialog.openInformation(null, "Fault", "It is necessary to open the scenario view!");
		}
	}

	private void removePaintScenario() {
		if (canvas != null && paintScenario != null)
			canvas.removePaintListener(paintScenario);
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public void setCurrent(FileScenario scenario) {
		this.current = scenario;
		try {
			removePaintScenario();
			addPaintScenario(new PaintScenario(current.getElements()));
		} catch (NullPointerException | IOException | CoreException e) {
			MessageDialog.openInformation(null, "Fault", "Could not open file");
		}
	}

	public FileScenario getCurrent() {
		return this.current;
	}

	public void redraw() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				canvas.redraw();
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

}