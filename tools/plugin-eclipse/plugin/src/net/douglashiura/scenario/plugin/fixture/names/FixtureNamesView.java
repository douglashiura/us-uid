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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

public class FixtureNamesView extends ViewPart {
	private List<IJavaProject> projects;
	private ViewControlable controlable;
	private Composite composite;
	private Composite parent;

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		parent.setLayout(new GridLayout(1, false));
		composite = new Composite(parent, SWT.NONE);
		try {
			loadProjects();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		composite.setLayout(fillLayout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		createInteraction();
		IActionBars bars = getViewSite().getActionBars();
		bars.getMenuManager().add(interactionButton());
		bars.getMenuManager().add(inputOutputButton());
		bars.getToolBarManager().add(reloadButton());
		parent.pack();
	}

	private void createInteraction() {
		clearComposite();
		controlable = new InteractionView(new Group(composite, SWT.NONE));
		controlable.createTable(projects);
		parent.pack();
	}

	private void clearComposite() {
		Control[] children = composite.getChildren();
		for (Control control : children) {
			control.dispose();
		}
	}

	private Action inputOutputButton() {
		Action inputOutput = new Action() {
			@Override
			public void run() {
				createInputsOutputs();
			}

			private void createInputsOutputs() {
				clearComposite();
				controlable = new InputOutputView(new Group(composite, SWT.NONE));
				controlable.createTable(projects);
				parent.pack();
			}
		};
		inputOutput.setText("Inputs and outputs");
		inputOutput.setToolTipText("Inputs and outputs");
		return inputOutput;
	}

	private Action interactionButton() {
		Action interactions = new Action() {
			@Override
			public void run() {
				createInteraction();
			}
		};
		interactions.setText("Interactions");
		interactions.setToolTipText("Interactions");
		return interactions;
	}

	private Action reloadButton() {
		Action reload = new Action() {
			@Override
			public void run() {
				reload();
			}
		};
		reload.setText("Reload");
		reload.setToolTipText("Reload");
		return reload;
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
			controlable.createItens(projects);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

}
