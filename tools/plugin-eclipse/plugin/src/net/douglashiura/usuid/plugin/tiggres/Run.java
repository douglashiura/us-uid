package net.douglashiura.usuid.plugin.tiggres;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;

import net.douglashiura.us.project.util.FileScenario;
import net.douglashiura.usuid.plugin.view.Runner;

@SuppressWarnings("restriction")
public class Run implements org.eclipse.debug.ui.ILaunchShortcut {

	@Override
	public void launch(ISelection item, String modo) {
		TreeSelection type = (TreeSelection) item;
		Object selection = type.getFirstElement();
		if (org.eclipse.jdt.internal.core.JavaProject.class.equals(selection.getClass())) {
			JavaProject java = (JavaProject) selection;
			Runner.getRunner().setContainer(java.getProject());
			try {
				Runner.getRunner().runAll();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		} else if (org.eclipse.core.internal.resources.Folder.class.equals(selection.getClass())) {
			Folder folder = (Folder) selection;
			Runner.getRunner().setContainer(folder);
			try {
				Runner.getRunner().runAll();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		} else if (org.eclipse.core.internal.resources.File.class.equals(selection.getClass())) {
			File file = (File) selection;
			Runner.getRunner().setCurrent(new FileScenario(file));
			Runner.getRunner().run();
		}
	}

	@Override
	public void launch(IEditorPart arg0, String arg1) {

	}

}