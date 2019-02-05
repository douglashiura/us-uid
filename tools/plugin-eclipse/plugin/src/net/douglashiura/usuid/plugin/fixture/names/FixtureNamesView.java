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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import net.douglashiura.leb.uid.scenario.glue.code.GlueCodeGenerate;
import net.douglashiura.us.serial.Results;
import net.douglashiura.usuid.plugin.type.Geometry;
import net.douglashiura.usuid.plugin.view.Runner;
import net.douglashiura.usuid.project.Element;
import net.douglashiura.usuid.project.Elementable;
import net.douglashiura.usuid.project.Elements;
import net.douglashiura.usuid.project.FileInteraction;

public class FixtureNamesView extends ViewPart {
	private List<IJavaProject> projects;
	private Table tableInputsOutputs;
	private Table tableInteractions;
	private Combo combo;

	@Override
	public void createPartControl(Composite parent) {
		loadProjects();
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		parent.setLayout(fillLayout);
		createTableInteractions(new Group(parent, SWT.NONE));
		createTableInputsOutputs(new Group(parent, SWT.NONE));

	}

	private void loadProjects() {
		projects = Projects.getJavaProjects();
	}

	private void createTableInputsOutputs(Group composite) {
		composite.setText("Inputs and outputs");
		composite.setLayout(new GridLayout(1, false));
		tableInputsOutputs = new Table(composite,
				SWT.BORDER | SWT.RESIZE | SWT.SCROLL_PAGE | SWT.V_SCROLL | SWT.H_SCROLL);
		tableInputsOutputs.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableInputsOutputs.setHeaderVisible(true);
		tableInputsOutputs.setLinesVisible(true);
		TableColumn file = new TableColumn(tableInputsOutputs, SWT.NONE);
		file.setWidth(219);
		file.setText("File");
		TableColumn interaction = new TableColumn(tableInputsOutputs, SWT.NONE);
		interaction.setWidth(246);
		interaction.setText("Interaction fixture name");
		TableColumn type = new TableColumn(tableInputsOutputs, SWT.NONE);
		type.setWidth(100);
		type.setText("Type");
		TableColumn fixtureName = new TableColumn(tableInputsOutputs, SWT.NONE);
		fixtureName.setWidth(246);
		fixtureName.setText("Fixture name");
		TableColumn value = new TableColumn(tableInputsOutputs, SWT.NONE);
		value.setWidth(246);
		value.setText("Value");
		createInputsOutputsItens();
		createControl(tableInputsOutputs, 3);
	}

	private void createInputsOutputsItens() {
		tableInputsOutputs.removeAll();
		try {
			List<Element> elements = Elements.ofInputsAndOutputsFrom(projects);
			for (Element element : elements) {
				TableItem item = new TableItem(tableInputsOutputs, SWT.NONE);
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
	}

	private void createControl(Table table, Integer columnEditable) {

		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		final ControlEditor editor2 = new ControlEditor(cursor);
		editor2.grabHorizontal = true;
		editor2.grabVertical = true;

		cursor.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				table.setSelection(new TableItem[] { cursor.getRow() });
				TableItem item = cursor.getRow();
				Elementable element = (Elementable) item.getData();
				element.getFileScenario().prepareToExecute();
				element.getElement().rate(Results.HIGHLIGHT.getColor());
				Runner.getRunner().setCurrent(element.getFileScenario());
				Geometry geometry = element.getElement().getGeometry();
				Runner.getRunner().setOrigin(geometry.getX(), geometry.getY());
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
								((Elementable) row.getData()).setFixtureName(text.getText());
							} catch (CoreException | IOException error) {
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

	private void createTableInteractions(Group composite) {
		composite.setLayout(new GridLayout(1, false));
		composite.setText("Interactions");
		Composite bar = new Composite(composite, SWT.NONE);
		bar.setLayout(new GridLayout(2, false));
		createMenuInteractions(bar);
		tableInteractions = new Table(composite,
				SWT.BORDER | SWT.RESIZE | SWT.SCROLL_PAGE | SWT.V_SCROLL | SWT.H_SCROLL);
		tableInteractions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableInteractions.setHeaderVisible(true);
		tableInteractions.setLinesVisible(true);
		TableColumn file = new TableColumn(tableInteractions, SWT.NONE);
		file.setWidth(219);
		file.setText("File");
		TableColumn fixtureName = new TableColumn(tableInteractions, SWT.NONE);
		fixtureName.setWidth(246);
		fixtureName.setText("Fixture name");
		TableColumn inputs = new TableColumn(tableInteractions, SWT.NONE);
		inputs.setWidth(246);
		inputs.setText("Inputs");
		TableColumn outputs = new TableColumn(tableInteractions, SWT.NONE);
		outputs.setWidth(246);
		outputs.setText("Ouputs");
		createInteracionsItens();
		createControl(tableInteractions, 1);
	}

	private void createInteracionsItens() {
		tableInteractions.removeAll();
		try {
			List<FileInteraction> elements = Elements.ofInteractionsFrom(projects);
			for (FileInteraction element : elements) {
				TableItem item = new TableItem(tableInteractions, SWT.NONE);
				item.setText(0, element.getFile());
				item.setText(1, element.getFixtureName());
				item.setText(2, element.getInputs());
				item.setText(3, element.getOutputs());
				item.setData(element);
			}
		} catch (CoreException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private void createMenuInteractions(Composite bar) {
		Group group = new Group(bar, SWT.BORDER);
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
		Group actions = new Group(bar, SWT.BORDER);
		actions.setLayout(new GridLayout(2, false));
		actions.setText("Actions");
		Button reload = new Button(actions, SWT.PUSH);
		reload.setText("Reload");
		reload.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				if (e.type == SWT.Selection) {
					loadProjects();
					createInputsOutputsItens();
					createInteracionsItens();
					addItensToComboProjets();
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

}
