package net.douglashiura.usuid.plugin.type;

import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public abstract class DrawInteraction implements Rateable {
	private java.awt.Color cor;

	public DrawInteraction() {
		cor = java.awt.Color.BLACK;
	}

	abstract List<InputGeometry> getInputs();

	abstract Geometry getGeometry();

	abstract List<OutputGeometry> getOutputs();

	abstract TransactionGeometry getTransaction();

	public void draw(GC gc) {
		Color saida = new Color(gc.getDevice(), cor.getRed(), cor.getGreen(), cor.getBlue());
		gc.setForeground(saida);
		Geometry geometry = getGeometry();
		gc.drawOval(geometry.getX(), geometry.getY(), geometry.getWidth(), geometry.getHeight());
	}

	@Override
	public void rate(java.awt.Color cor) {
		this.cor = cor;
	}
}
