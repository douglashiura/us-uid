package org.eclipse.swt.widgets;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;

import net.douglashiura.scenario.plugin.type.Rateable;
import net.douglashiura.scenario.project.util.FileScenario;
import net.douglashiura.scenario.project.util.Notificable;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;

public class ItemScenario extends TreeItem implements Notificable {

	private FileScenario aScenario;
	private Map<Integer, ItemPath> paths;

	public ItemScenario(Tree arvoreDeElementos, FileScenario aScenario) {
		super(arvoreDeElementos, SWT.NONE);
		this.aScenario = aScenario;
		setText(aScenario.getName());
		if (aScenario.getPaths().size() > 1) {
			paths = new HashMap<>();
		}
	}

	public FileScenario getScenario() {
		return aScenario;
	}

	@Override
	public void addResult(Result result, Rateable element) {
		Display.getDefault().asyncExec(new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				while (null == ItemScenario.this) {
					try {
						wait(0, 10);
					} catch (InterruptedException e) {
					}
				}
				if (aScenario.getPaths().size() > 1) {
					ItemPath item = getPath(result);
					item.addResult(result, element);
				} else {
					new ItemResult(ItemScenario.this, element, result, aScenario);
				}
			}

			private ItemPath getPath(Result result) {
				ItemPath path = paths.get(result.getIndex());
				if (path == null) {
					path = new ItemPath(ItemScenario.this, result.getIndex(), aScenario);
					paths.put(result.getIndex(), path);
				}
				return path;
			}
		});
	}

	public void prepareToExecute() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				removeAll();
				setText(aScenario.getName());
				aScenario.prepareToExecute();
			}
		});
	}

	@Override
	public void finishyATestExecution(Results generalResult) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				setText(String.format("%s (%s)", aScenario.getName(), generalResult));
			}
		});
		for (ItemPath item : paths.values()) {
			item.finishyATestExecution(generalResult);
		}
	}

}