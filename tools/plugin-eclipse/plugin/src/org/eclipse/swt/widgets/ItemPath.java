package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

import net.douglashiura.us.project.util.FileScenario;
import net.douglashiura.us.project.util.Notificable;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;
import net.douglashiura.usuid.plugin.type.Rateable;

public class ItemPath extends TreeItem implements Notificable {

	private ItemScenario itemScenario;

	public ItemPath(ItemScenario itemScenario) {
		super(itemScenario, SWT.NONE);
		this.itemScenario = itemScenario;
		setText("0");
	}

	@Override
	public void addResult(Result result, Rateable element) {
		new ItemResult(ItemPath.this, element, result);
	}

	@Override
	public void finishyATestExecution(Results generalResult) {

	}

	public FileScenario getScenario() {
		return itemScenario.getScenario();
	}

}
