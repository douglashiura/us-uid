package net.douglashiura.scenario.plugin.editor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import net.douglashiura.scenario.project.util.FileScenario;
import net.douglashiura.us.serial.InputFile;
import net.douglashiura.us.serial.Interaction;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;

public class Executor {

	private Integer complete;
	private Integer faults;
	private Integer errors;
	private PathScenario scenarioProcessing;
	private Iterator<PathScenario> scenarios;
	private Integer total;
	private Processor processor;

	public Executor(List<FileScenario> scenarios) throws IOException {
		List<PathScenario> manyPaths = new ArrayList<PathScenario>();
		for (FileScenario fileScenario : scenarios) {
			List<Interaction> paths = fileScenario.getPaths();
			Integer index = 0;
			for (Interaction scenario : paths) {
				manyPaths.add(new PathScenario(fileScenario, scenario, index++));
			}
		}
		this.total = manyPaths.size();
		this.scenarios = manyPaths.iterator();
		prepareExeution();
	}

	public void execute(IProject project) throws CoreException, IOException, InterruptedException {
		processor = new Processor(project, this);
		next();
	}

	private void next() throws IOException {
		if (scenarios.hasNext()) {
			scenarioProcessing = scenarios.next();
			FileScenario fileScenario = scenarioProcessing.getFileScenario();
			fileScenario.prepareToExecute();
			processor.write(new InputFile(fileScenario.getName(), scenarioProcessing.getScenario(),
					scenarioProcessing.getIndex()));
		} else {
			processor.exit();
		}
	}

	public void delivery(Result result) {
		scenarioProcessing.getFileScenario().addResult(result);
		if (Results.isExecutionFinishy(result.getResult())) {
			try {
				next();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void prepareExeution() {
		complete = 0;
		faults = 0;
		errors = 0;
	}

	public Integer getComplete() {
		return complete;
	}

	public Integer getErrors() {
		return errors;
	}

	public Integer getFaults() {
		return faults;
	}

	public Integer getTotal() {
		return total;
	}

	public void updateStatusExecution(Results status) {
		complete++;
		if (Results.ERROR.equals(status))
			errors++;
		else if (Results.FAIL.equals(status))
			faults++;
	}

}