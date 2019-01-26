package net.douglashiura.usuid.plugin.editor;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;

import net.douglashiura.usuid.plugin.type.InteractionGeometry;
import net.douglashiura.usuid.plugin.type.TransactionGeometry;

public class PaintScenario implements PaintListener {
	private InteractionGeometry scenario;
	public PaintScenario(InteractionGeometry state) {
		this.scenario = state;
	}
	@Override
	public void paintControl(PaintEvent e) {
		InteractionGeometry state = scenario;
		while (state != null) {
			state.draw(e.gc);
			state = next(state);
		}
	}
	private InteractionGeometry next(InteractionGeometry state) {
		TransactionGeometry transaction = state.getTransaction();
		return transaction == null ? null : transaction.getTarget();
	}
}
