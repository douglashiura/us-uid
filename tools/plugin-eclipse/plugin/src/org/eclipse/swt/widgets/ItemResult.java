package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

import net.douglashiura.scenario.plugin.type.InteractionGeometry;
import net.douglashiura.scenario.plugin.type.Rateable;
import net.douglashiura.scenario.plugin.type.TransactionGeometry;
import net.douglashiura.scenario.project.util.FileScenario;
import net.douglashiura.us.serial.Result;

public class ItemResult extends TreeItem {

	private Rateable element;
	private Result result;
	private ItemScenario item;

	public ItemResult(ItemScenario item, Rateable element, Result result) {
		super(item, SWT.NONE);
		this.item = item;
		this.element = element;
		this.result = result;
		String identification = "";
		if (InteractionGeometry.class.equals(element.getType())
				|| TransactionGeometry.class.equals(element.getType())) {
			identification = element.getFixtureName();
		} else {
			identification = element.getValue();
		}
		setText(String.format("(%s) %s", result.getResult(), identification));
	}

	public Rateable getElement() {
		return element;
	}

	public Result getResult() {
		return result;
	}

	public FileScenario getItemScenario() {
		return item.getScenario();
	}
}