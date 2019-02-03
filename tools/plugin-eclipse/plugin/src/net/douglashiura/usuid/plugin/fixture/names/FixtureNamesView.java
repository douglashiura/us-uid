package net.douglashiura.usuid.plugin.fixture.names;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import net.douglashiura.usuid.project.Element;
import net.douglashiura.usuid.project.Elements;
import net.douglashiura.usuid.project.FileInteraction;
import net.douglashiura.usuid.project.SetFixtureName;

public class FixtureNamesView extends ViewPart {
	private List<IJavaProject> projects;

	@Override
	public void createPartControl(Composite parent) {
		projects = Projects.getJavaProjects();
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		parent.setLayout(fillLayout);
		Composite composite = new Composite(parent, SWT.NONE);
		Composite composite2 = new Composite(parent, SWT.NONE);
		createTableInteractions(composite);
		createTableInputsOutputs(composite2);
	}

	private void createTableInputsOutputs(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
		Label label = new Label(composite, SWT.BORDER);
		label.setText("Inputs and outputs");
		Table table = new Table(composite, SWT.BORDER | SWT.RESIZE | SWT.SCROLL_PAGE | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn file = new TableColumn(table, SWT.NONE);
		file.setWidth(219);
		file.setText("File");
		TableColumn interaction = new TableColumn(table, SWT.NONE);
		interaction.setWidth(246);
		interaction.setText("Interaction fixture name");
		TableColumn type = new TableColumn(table, SWT.NONE);
		type.setWidth(100);
		type.setText("Type");
		TableColumn fixtureName = new TableColumn(table, SWT.NONE);
		fixtureName.setWidth(246);
		fixtureName.setText("Fixture name");
		TableColumn value = new TableColumn(table, SWT.NONE);
		value.setWidth(246);
		value.setText("Value");
		try {
			List<Element> elements = net.douglashiura.usuid.project.Elements.ofInputsAndOutputsFrom(projects);
			for (Element element : elements) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(0, element.getFile());
				item.setText(1, element.getInteractionFixtureName());
				item.setText(2, element.getType());
				item.setText(3, element.getFixtureName());
				item.setText(4, element.getValue());
				item.setData(element);
			}
		} catch (CoreException | IOException e1) {
			e1.printStackTrace();
		}
		createControl(table, 3);
	}

	private void createControl(Table table, Integer columnEditable) {

		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		final ControlEditor editor2 = new ControlEditor(cursor);
		editor2.grabHorizontal = true;
		editor2.grabVertical = true;

		cursor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				int column = cursor.getColumn();
				if (column == columnEditable) {
					final Text text = new Text(cursor, SWT.NONE);
					TableItem row = cursor.getRow();
					text.setText(row.getText(column));
					text.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e) {
							try {
								((SetFixtureName) row.getData()).setFixtureName(text.getText());
							} catch (CoreException error) {
								error.printStackTrace();
							}
							int column = cursor.getColumn();
							if (e.character == SWT.CR && column == columnEditable) {
								TableItem row = cursor.getRow();
								row.setText(column, text.getText());
								text.dispose();
							}
							if (e.character == SWT.ESC) {
								text.dispose();
							}
						}
					});
					editor2.setEditor(text);
					text.setFocus();
				}
			}
		});

	}

	private void createTableInteractions(Composite composite) {
		composite.setLayout(new GridLayout(1, false));
		Label label = new Label(composite, SWT.BORDER);
		label.setText("Interactions");
		Table table = new Table(composite, SWT.BORDER | SWT.RESIZE | SWT.SCROLL_PAGE | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn file = new TableColumn(table, SWT.NONE);
		file.setWidth(219);
		file.setText("File");
		TableColumn fixtureName = new TableColumn(table, SWT.NONE);
		fixtureName.setWidth(246);
		fixtureName.setText("Fixture name");
		TableColumn inputs = new TableColumn(table, SWT.NONE);
		inputs.setWidth(246);
		inputs.setText("Inputs");
		TableColumn outputs = new TableColumn(table, SWT.NONE);
		outputs.setWidth(246);
		outputs.setText("Ouputs");
		try {
			List<FileInteraction> elements = Elements.ofInteractionsFrom(projects);
			for (FileInteraction element : elements) {
				TableItem item = new TableItem(table, SWT.NONE);
				item.setText(0, element.getFile());
				item.setText(1, element.getFixtureName());
				item.setText(2, element.getInputs());
				item.setText(3, element.getOutputs());
				item.setData(element);
			}
		} catch (CoreException | IOException e1) {
			e1.printStackTrace();
		}
		createControl(table, 1);
	}

	@Override
	public void setFocus() {

	}

}
