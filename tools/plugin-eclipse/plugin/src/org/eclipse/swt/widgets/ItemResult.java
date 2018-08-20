package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

import net.douglashiura.us.project.util.FileScenario;
import net.douglashiura.us.serial.Result;
import net.douglashiura.usuid.plugin.type.Interaction;
import net.douglashiura.usuid.plugin.type.Rateable;
import net.douglashiura.usuid.plugin.type.Transaction;

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
		if (Interaction.class.equals(element.getType()) || Transaction.class.equals(element.getType()))
			identification = element.getFixture();
		else
			identification = element.getValue();
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
