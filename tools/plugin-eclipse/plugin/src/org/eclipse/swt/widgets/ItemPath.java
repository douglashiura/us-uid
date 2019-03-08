package org.eclipse.swt.widgets;

import java.util.ArrayList;

import org.eclipse.swt.SWT;

import net.douglashiura.scenario.plugin.type.Rateable;
import net.douglashiura.scenario.project.util.FileScenario;
import net.douglashiura.scenario.project.util.Notificable;
import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;

public class ItemPath extends TreeItem implements Notificable {

	private FileScenario scenario;
	private java.util.List<Result> results;
	private Integer indexPath;

	public ItemPath(ItemScenario item, Integer indexPath, FileScenario scenario) {
		super(item, SWT.NONE);
		this.indexPath = indexPath;
		this.scenario = scenario;
		setText(indexPath.toString());
		results = new ArrayList<Result>();
	}

	@Override
	public void addResult(Result result, Rateable element) {
		results.add(result);
		new ItemResult(ItemPath.this, element, result, scenario);
	}

	@Override
	public void finishyATestExecution(Results generalResult) {
		Results generalResult2 = checkGeneralResult();
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				setText(String.format("%s (%s)", indexPath, generalResult2));
			}

		});
	}

	private Results checkGeneralResult() {
		for (Result result : results) {
			if (Results.ERROR.equals(result.getResult())||Results.FAIL.equals(result.getResult())) {
				return result.getResult();
			}
		}
		return Results.OK;
	}

}