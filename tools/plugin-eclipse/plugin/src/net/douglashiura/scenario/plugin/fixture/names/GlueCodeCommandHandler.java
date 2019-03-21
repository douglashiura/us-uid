package net.douglashiura.scenario.plugin.fixture.names;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.handlers.HandlerUtil;

import net.douglashiura.scenario.glue.code.GlueCodeGenerate;

public class GlueCodeCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TreeSelection aEvent = (TreeSelection) HandlerUtil.getCurrentSelection(event);
		if (aEvent.getFirstElement() instanceof Project) {
			Project aProject = (org.eclipse.core.internal.resources.Project) aEvent.getFirstElement();
			try {
				DialogPackage dialog = new DialogPackage(null);
				dialog.create();
				if (dialog.open() == Window.OK) {
					new GlueCodeGenerate(dialog.getPackage(), aProject).write();				 
				}
			} catch (IOException | CoreException e1) {
				e1.printStackTrace();
			}
		}else {
			MessageDialog.openInformation(null, "Fault", "Select a Java project!");
		}
		
		return null;
	}

}
