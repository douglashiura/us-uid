package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

public class ItemPath extends TreeItem {


	public ItemPath(ItemScenario item, Integer indexPath) {
		super(item, SWT.NONE);
		setText(indexPath.toString());
	}

}