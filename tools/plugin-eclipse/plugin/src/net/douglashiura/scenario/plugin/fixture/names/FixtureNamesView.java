package net.douglashiura.scenario.plugin.fixture.names;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

public class FixtureNamesView extends ViewPart {
	private List<IJavaProject> projects;
	private InteractionView interactionView;
	private InputOutputView inputOutputView;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Composite composite = new Composite(parent, SWT.NONE);
		try {
			loadProjects();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		composite.setLayout(fillLayout);
		Group group = new Group(composite, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		inputOutputView = new InputOutputView(group);
		interactionView = new InteractionView(new Group(composite, SWT.NONE));
		inputOutputView.createTableInputsOutputs(projects);
		interactionView.createTableInteractions(projects);

		IActionBars bars = getViewSite().getActionBars();
		Action reload = new Action() {
			@Override
			public void run() {
				reload();
			}
		};
		reload.setText("Reload");
		reload.setToolTipText("Reload");

		bars.getMenuManager().add(reload);
		bars.getToolBarManager().add(reload);
		parent.pack();

	}

	private void loadProjects() throws CoreException {
		projects = Projects.getJavaProjects();
	}

	@Override
	public void setFocus() {
	}

	public void reload() {
		try {
			loadProjects();
			inputOutputView.createInputsOutputsItens(projects);
			interactionView.createInteracionsItens(projects);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

}
