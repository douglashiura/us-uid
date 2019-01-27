package net.douglashiura.usuid.plugin.editor;

import java.util.Collection;
import java.util.List;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import net.douglashiura.usuid.plugin.type.Rateable;

public class PaintScenario implements PaintListener {
	private Collection<Rateable> scenario;

	public PaintScenario(Collection<Rateable> states) {
		this.scenario = states;
	}

	@Override
	public void paintControl(PaintEvent e) {
		for (Rateable elemenet : scenario) {
			elemenet.draw(e.gc);
		}
	}

//	private void print(InteractionGeometry state, PaintEvent e) {
//		state.draw(e.gc);
//		if (state.getTransaction() != null) {
//			for (InteractionGeometry geometry : state.getTransaction().getTargets()) {
//				print(geometry, e);
//			}
//		}
//	}
}
