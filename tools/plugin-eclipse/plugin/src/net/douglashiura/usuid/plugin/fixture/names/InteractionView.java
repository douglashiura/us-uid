package net.douglashiura.usuid.plugin.fixture.names;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import net.douglashiura.usuid.project.Elements;
import net.douglashiura.usuid.project.FileInteraction;

public class InteractionView {

	private Table tableInteractions;
	private Group group;
	private List<FileInteraction> elements;

	public InteractionView(Group group) {
		this.group = group;
		group.setLayout(new GridLayout(1, false));
		group.setText("Interactions");

	}

	void createTableInteractions(List<IJavaProject> projects) {
		tableInteractions = new Table(group, SWT.BORDER | SWT.RESIZE | SWT.SCROLL_PAGE | SWT.V_SCROLL | SWT.H_SCROLL);
		tableInteractions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableInteractions.setHeaderVisible(true);
		tableInteractions.setLinesVisible(true);
		TableColumn file = new TableColumn(tableInteractions, SWT.NONE);
		file.setWidth(219);
		file.setText("File");
		TableColumn fixtureName = new TableColumn(tableInteractions, SWT.NONE);
		fixtureName.setWidth(246);
		fixtureName.setText("Fixture name (click to auto naming)");
		SelectionListener listener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				autoNamingFixtures();
				createInteracionsItens(projects);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		};
		fixtureName.addSelectionListener(listener);
		TableColumn inputs = new TableColumn(tableInteractions, SWT.NONE);
		inputs.setWidth(246);
		inputs.setText("Inputs");
		TableColumn outputs = new TableColumn(tableInteractions, SWT.NONE);
		outputs.setWidth(246);
		outputs.setText("Ouputs");
		createInteracionsItens(projects);
		new Control(tableInteractions, 1);
	}

	void createInteracionsItens(List<IJavaProject> projects) {
		tableInteractions.removeAll();
		try {
			elements = Elements.ofInteractionsFrom(projects);
			for (FileInteraction element : elements) {
				TableItem item = new TableItem(tableInteractions, SWT.NONE);
				item.setText(0, element.getFile());
				item.setText(1, element.getFixtureName());
				item.setText(2, element.getInputs().toString());
				item.setText(3, element.getOutputs().toString());
				item.setData(element);
			}
		} catch (CoreException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public void autoNamingFixtures() {
		try {
			new AutoNameByUniformity(elements).naming();
		} catch (CoreException | IOException e) {
			e.printStackTrace();
		}
	}

}
