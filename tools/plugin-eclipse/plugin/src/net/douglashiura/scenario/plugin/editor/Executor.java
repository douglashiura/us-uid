package net.douglashiura.scenario.plugin.editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import net.douglashiura.scenario.project.util.FileScenario;
import net.douglashiura.us.serial.InputFile;
import net.douglashiura.us.serial.Interaction;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;

public class Executor {

	private ServerSocket socket;
	private boolean hasAnotherExecution;
	private boolean execution;
	private List<FileScenario> scenarios;
	private Integer complete;
	private Integer faults;
	private Integer errors;
	private ILaunch launch;

	public Executor(Shell shell, List<FileScenario> scenarios) throws IOException {
		this.scenarios = scenarios;
		prepareExeution();
		try {
			socket = new ServerSocket(6969);
			Thread teste = new Thread(new Work());
			teste.start();
		} catch (BindException e) {
			MessageDialog.openInformation(shell, "Fault", "There are another execution!");
			hasAnotherExecution = true;
		}
	}

	private void prepareExeution() {
		complete = 0;
		faults = 0;
		errors = 0;
	}

	class Work implements Runnable {
		public void run() {
			execution = true;
			Socket client = null;
			try {
				client = socket.accept();
				ObjectOutputStream writer = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream input = new ObjectInputStream(client.getInputStream());
				for (FileScenario scenario : scenarios) {
					List<Interaction> paths = scenario.getPaths();
					for (int index = 0; index < paths.size(); index++) {
						InputFile composite = new InputFile(scenario.getName(), paths.get(index), index);
						executeAScenario(scenario, composite, writer, input);
					}
				}
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				execution = false;
				closeSocket();
			}
		}

		private void executeAScenario(FileScenario scenario, InputFile composite, ObjectOutputStream writer,
				ObjectInputStream input) throws IOException, ClassNotFoundException {
			writer.writeObject(composite);
			scenario.prepareToExecute();
			Result result = null;
			while ((result = (Result) input.readObject()) != null) {
				scenario.addResult(result);
				if (Results.isExecutionFinishy(result.getResult())) {
					return;
				}
			}
		}

		private void closeSocket() {
			try {
				socket.close();
			} catch (IOException e1) {
			}
			try {
				launch.terminate();
			} catch (DebugException e) {
				e.printStackTrace();
			}
		}

	}

	public void execute(IProject project) throws CoreException, IOException {
		if (!hasAnotherExecution) {
			ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
			ILaunchConfigurationType type = manager
					.getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
			ILaunchConfigurationWorkingCopy wc = type.newInstance(null, "User Scenario");
			wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName());
			wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, "net.douglashiura.us.run.Executor");
			ILaunchConfiguration config = wc.doSave();
			launch = config.launch(ILaunchManager.RUN_MODE, null);
			wc.delete();
			config.delete();
		}
	}

	public boolean getExecution() {
		return execution;
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
		return scenarios.size();
	}

	public void updateStatusExecution(Results status) {
		complete++;
		if (Results.ERROR.equals(status))
			errors++;
		else if (Results.FAIL.equals(status))
			faults++;
	}
}