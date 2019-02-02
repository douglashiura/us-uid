package net.douglashiura.usuid.plugin.fixture.names;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

public class FixtureNamesView extends ViewPart {
	@Override
	public void createPartControl(Composite parent) {
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		parent.setLayout(fillLayout);
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(new Color(parent.getDisplay(), 75, 100, 255));
		Composite composite2 = new Composite(parent, SWT.NULL);
		composite2.setBackground(new Color(parent.getDisplay(), 255, 100, 75));
		tableCreate(composite);
		tableCreate(composite2);
	}

	private void tableCreate(Composite composite) {
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);
		Label label = new Label(composite, SWT.BORDER);
		label.setText("Interactions");
		Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.RESIZE | SWT.SCROLL_PAGE|SWT.V_SCROLL);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd_table.heightHint = 136;
		gd_table.widthHint = 205;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(37);

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(219);
		tblclmnNewColumn_1.setText("Item ID");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(246);
		tblclmnNewColumn_2.setText("Revision ID");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(246);
		tblclmnNewColumn_3.setText("Vendor Name");

		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(246);
		tblclmnNewColumn_4.setText("Vendor ID");

		for (int i = 0; i < 10; i++) {
			TableItem item = new TableItem(table, SWT.NONE);

			item.setText(1, "item_id" + i);
			item.setText(2, "item_revision_id" + i);
			item.setText(3, "object_string" + i);
		}

	}

	@Override
	public void setFocus() {

	}

}
