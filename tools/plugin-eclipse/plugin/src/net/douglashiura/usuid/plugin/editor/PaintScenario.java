package net.douglashiura.usuid.plugin.editor;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import net.douglashiura.usuid.plugin.type.Interaction;
import net.douglashiura.usuid.plugin.type.Transaction;

public class PaintScenario implements PaintListener {
	private Interaction scenario;
	public PaintScenario(Interaction state) {
		this.scenario = state;
	}
	@Override
	public void paintControl(PaintEvent e) {
		Interaction state = scenario;
		while (state != null) {
			state.draw(e.gc);
			state = next(state);
		}
	}
	private Interaction next(Interaction state) {
		Transaction transaction = state.getTransaction();
		return transaction == null ? null : transaction.getTarget();
	}
}
