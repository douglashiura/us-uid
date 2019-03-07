package net.douglashiura.scenario.plugin.view;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ItemResult;
import org.eclipse.swt.widgets.ItemScenario;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

public class TreeSelection implements Listener {

	private ViewTests view;

	public TreeSelection(ViewTests view) {
		this.view = view;
	}

	@Override
	public void handleEvent(Event event) {
		selectItemTree();
	}

	void selectItemTree() {
		TreeItem[] selectedItem = view.getTree().getSelection();
		if (selectedItem.length > 0)
			if (selectedItem[0] instanceof ItemScenario) {
				ItemScenario itemScenario = (ItemScenario) selectedItem[0];
				Runner.getRunner().setCurrent(itemScenario.getScenario());
			} else if (selectedItem[0] instanceof ItemResult) {
				ItemResult result = (ItemResult) selectedItem[0];
				Runner.getRunner().setCurrent(result.getItemScenario());
				view.setResult(result.getResult(), result.getElement());
			}
	}
}
