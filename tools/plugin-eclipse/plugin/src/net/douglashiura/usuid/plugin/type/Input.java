package net.douglashiura.usuid.plugin.type;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public class Input extends AbstractType implements Rateable {
	private java.awt.Color cor = java.awt.Color.BLACK;

	public Input(String id, Geometry geometry, String model, String value) {
		super(id, geometry, model, value);
	}

	public void draw(GC gc) {
		Color saida = new Color(gc.getDevice(), cor.getRed(), cor.getGreen(), cor.getBlue());
		gc.setForeground(saida);
		Geometry geometry = getGeometry();
		gc.drawRectangle(geometry.getX(), geometry.getY(), geometry.getWidth(), geometry.getHeight());
		gc.drawText(getValue(), geometry.getX() + 3, geometry.getY() + 3);
	}

	@Override
	public void rate(java.awt.Color cor) {
		this.cor = cor;
	}

	@Override
	public Class<?> getType() {
		return Input.class;
	}

}
