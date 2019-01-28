package net.douglashiura.usuid.plugin.type;

import java.util.UUID;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public class OutputGeometry extends AbstractType implements Rateable {
	private java.awt.Color cor = java.awt.Color.BLACK;

	public OutputGeometry(UUID id, Geometry geometry, String model, String value) {
		super(id, geometry, model, value);
	}

	public void draw(GC gc) {
		Color saida = new Color(gc.getDevice(), cor.getRed(), cor.getGreen(), cor.getBlue());
		gc.setForeground(saida);
		Geometry geometry = getGeometry();
		gc.drawText(getValue(), geometry.getX() + 3, geometry.getY() + 3);
	}

	@Override
	public void rate(java.awt.Color cor) {
		this.cor = cor;
	}

	@Override
	public Class<?> getType() {
		return OutputGeometry.class;
	}
}
