package net.douglashiura.usuid.plugin.fixture.names;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import net.douglashiura.leb.uid.scenario.glue.code.GlueCodeGenerate;

public class FixtureNamesView extends ViewPart {
	private List<IJavaProject> projects;
	private Combo combo;
	private InteractionView interactionView;
	private InputOutputView inputOutputView;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		loadProjects();
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		parent.setLayout(fillLayout);
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		createMenuInteractions(group);
		inputOutputView = new InputOutputView(group);
		interactionView = new InteractionView(new Group(parent, SWT.NONE));
		inputOutputView.createTableInputsOutputs(projects);
		interactionView.createTableInteractions(projects);
	}

	private void loadProjects() {
		projects = Projects.getJavaProjects();
	}

	private void createMenuInteractions(Composite bar) {
		Composite composite = new Composite(bar, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		Group group = new Group(composite, SWT.BORDER);
		group.setText("Glue code genarator");
		group.setLayout(new GridLayout(3, false));
		combo = new Combo(group, SWT.BORDER);
		addItensToComboProjets();
		Text _package = new Text(group, SWT.BORDER);
		_package.setText("com.company.sample");
		Button generate = new Button(group, SWT.PUSH);
		generate.setText("Generate");
		generate.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if (e.type == SWT.Selection) {
					IJavaProject project = projects.get(combo.getSelectionIndex());
					try {
						new GlueCodeGenerate(_package.getText(), project).write();
					} catch (IOException | CoreException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		Group actions = new Group(composite, SWT.BORDER);
		actions.setLayout(new GridLayout(2, false));
		actions.setText("Actions");
		Button reload = new Button(actions, SWT.PUSH);
		reload.setText("Reload");
		reload.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if (e.type == SWT.Selection) {
					reload();
				}
			}
		});
	}

	private void addItensToComboProjets() {
		combo.removeAll();
		for (IJavaProject iJavaProject : projects) {
			combo.add(iJavaProject.getProject().getName());
		}
	}

	@Override
	public void setFocus() {

	}

	public void reload() {
		loadProjects();
		inputOutputView.createInputsOutputsItens(projects);
		interactionView.createInteracionsItens(projects);
		addItensToComboProjets();
	}

}
