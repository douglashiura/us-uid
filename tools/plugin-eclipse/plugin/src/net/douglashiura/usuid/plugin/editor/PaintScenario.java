package net.douglashiura.usuid.plugin.editor;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import net.douglashiura.usuid.plugin.type.InteractionGeometry;

public class PaintScenario implements PaintListener {
	private InteractionGeometry scenario;

	public PaintScenario(InteractionGeometry state) {
		this.scenario = state;
	}

	@Override
	public void paintControl(PaintEvent e) {
		print(scenario, e);
	}

	private void print(InteractionGeometry state, PaintEvent e) {
		state.draw(e.gc);
		if (state.getTransaction() != null) {
			for (InteractionGeometry geometry : state.getTransaction().getTargets()) {
				print(geometry, e);
			}
		}
	}
}
