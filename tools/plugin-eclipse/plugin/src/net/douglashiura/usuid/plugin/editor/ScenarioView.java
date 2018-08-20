package net.douglashiura.usuid.plugin.editor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import net.douglashiura.usuid.plugin.view.Runner;

public class ScenarioView extends ViewPart {

	public void createPartControl(Composite parent) {
		ScrolledComposite uid = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		Canvas canvas = new Canvas(uid, SWT.NONE);
		canvas.setSize(1800, 3000);
		uid.setContent(canvas);
		uid.pack();
		Runner.getRunner().setCanvas(canvas);
	}
	public void setFocus() {
		// set focus to my widget. For a label, this doesn't
		// make much sense, but for more complex sets of widgets
		// you would decide which one gets the focus.
	}
}