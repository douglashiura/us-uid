package net.douglashiura.usuid.plugin.tiggres;

import java.io.IOException;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;

import net.douglashiura.usuid.plugin.view.Runner;
import net.douglashiura.usuid.project.util.FileScenario;

public class Run implements org.eclipse.debug.ui.ILaunchShortcut {

	@Override
	public void launch(ISelection item, String modo) {
		System.out.println("Run.launch()");
		TreeSelection type = (TreeSelection) item;
		Object selection = type.getFirstElement();
		if (org.eclipse.jdt.internal.core.JavaProject.class.equals(selection.getClass())) {
			JavaProject java = (JavaProject) selection;
			Runner.getRunner().setContainer(java.getProject());
			try {
				Runner.getRunner().runAll();
			} catch (CoreException | IOException e) {
				e.printStackTrace();
			}
		} else if (org.eclipse.core.internal.resources.Folder.class.equals(selection.getClass())) {
			Folder folder = (Folder) selection;
			Runner.getRunner().setContainer(folder);
			try {
				Runner.getRunner().runAll();
			} catch (CoreException | IOException e) {
				e.printStackTrace();
			}
		} else if (org.eclipse.core.internal.resources.File.class.equals(selection.getClass())) {
			File file = (File) selection;

			try {
				Runner.getRunner().setCurrent(new FileScenario(file));
				Runner.getRunner().run();
			} catch (IOException | CoreException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void launch(IEditorPart arg0, String arg1) {
		System.out.println("Run.launch(2)");

	}

}