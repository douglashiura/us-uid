package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;

import net.douglashiura.us.project.util.FileScenario;
import net.douglashiura.us.project.util.Notificable;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;
import net.douglashiura.usuid.plugin.type.Rateable;

public class ItemScenario extends TreeItem implements Notificable {

	private FileScenario aScenario;

	public ItemScenario(Tree arvoreDeElementos, FileScenario aScenario) {
		super(arvoreDeElementos, SWT.NONE);
		this.aScenario = aScenario;
		setText(aScenario.getName());
	}

	public FileScenario getScenario() {
		return aScenario;
	}

	@Override
	public void addResult(Result result, Rateable element) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				new ItemResult(ItemScenario.this, element, result);
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

	}
}