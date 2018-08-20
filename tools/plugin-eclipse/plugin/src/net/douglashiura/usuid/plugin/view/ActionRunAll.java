package net.douglashiura.usuid.plugin.view;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

public class ActionRunAll extends Action {

	private static final String RUN_FOLDER = "Run All";

	public ActionRunAll(ViewTests view) {
		setText(RUN_FOLDER);
		setToolTipText(RUN_FOLDER);
		setImageDescriptor(
				PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(view.getContentDescription()));
	}

	@Override
	public void run() {
		try {
			Runner.getRunner().runAll();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

}
