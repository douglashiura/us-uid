package net.douglashiura.scenario.plugin.editor;

import java.util.Collection;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import net.douglashiura.scenario.plugin.type.Rateable;

public class PaintScenario implements PaintListener {
	private Collection<Rateable> scenario;

	public PaintScenario(Collection<Rateable> states) {
		this.scenario = states;
	}

	@Override
	public void paintControl(PaintEvent e) {
		for (Rateable element : scenario) {
			element.draw(e.gc);
		}
	}
}
