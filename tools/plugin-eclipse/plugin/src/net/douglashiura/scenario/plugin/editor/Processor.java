package net.douglashiura.scenario.plugin.editor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;

import net.douglashiura.us.serial.InputFile;
import net.douglashiura.us.serial.Result;

public class Processor implements Runnable {

	private Executor executor;
	private ILaunch launch;
	private ServerSocket server;
	private Socket client;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Thread reader;

	public Processor(IProject project, Executor executor) throws CoreException, IOException, InterruptedException {
		this.executor = executor;
		server = new ServerSocket(0);
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager
				.getLaunchConfigurationType(IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
		ILaunchConfigurationWorkingCopy wc = type.newInstance(null, "User Scenario");
		wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName());
		wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_MAIN_TYPE_NAME, "net.douglashiura.us.run.Executor");
		wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROGRAM_ARGUMENTS,
				Integer.toString(server.getLocalPort()));
		ILaunchConfiguration configuration = wc.doSave();
		launch = configuration.launch(ILaunchManager.RUN_MODE, new NullProgressMonitor());
		wc.delete();
		configuration.delete();
		reader = new Thread(this);
		reader.start();
		while (output == null) {
			Thread.sleep(1);
		}
	}

	public void write(InputFile scenario) throws IOException {
		output.writeObject(scenario);
		output.flush();
	}

	@Override
	public void run() {
		try {
			client = server.accept();
			output = new ObjectOutputStream(client.getOutputStream());
			input = new ObjectInputStream(client.getInputStream());
			Result result;
			while ((result = (Result) input.readObject()) != null) {
				executor.delivery(result);
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		try {
			reader.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			launch.terminate();
		} catch (DebugException e) {
			e.printStackTrace();
		}
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
