package net.douglashiura.usuid.plugin.type;

import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public abstract class DrawInteraction implements Rateable {
	private java.awt.Color cor = java.awt.Color.BLACK;

	abstract List<Input> getInputs();

	abstract Geometry getGeometry();

	abstract List<Output> getOutputs();

	abstract Transaction getTransaction();


	public void draw(GC gc) {
		Color saida = new Color(gc.getDevice(), cor.getRed(), cor.getGreen(), cor.getBlue());
		gc.setForeground(saida);
		Geometry geometry = getGeometry();
		gc.drawOval(geometry.getX(), geometry.getY(), geometry.getWidth(), geometry.getHeight());
		drawTransaction(gc);
		drawInputs(gc);
		drawOutputs(gc);
	}

	private void drawTransaction(GC gc) {
		if (getTransaction() == null)
			return;
		getTransaction().draw(gc);
	}

	private void drawOutputs(GC gc) {
		for (Output output : getOutputs())
			output.draw(gc);
	}

	private void drawInputs(GC gc) {
		for (Input input : getInputs())
			input.draw(gc);
	}

	@Override
	public void rate(java.awt.Color cor) {
		this.cor = cor;
	}


}