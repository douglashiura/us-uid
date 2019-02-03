package net.douglashiura.usuid.plugin.type;

import java.util.UUID;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

import com.google.gson.internal.LinkedTreeMap;

public class InputGeometry extends AbstractType implements Rateable {
	private java.awt.Color color;

	public InputGeometry(LinkedTreeMap<String, ?> objectJson, UUID id, Geometry geometry, String model, String value) {
		super(objectJson, id, geometry, model, value);
		color = java.awt.Color.BLACK;
	}

	public void draw(GC gc) {
		Color aColor = new Color(gc.getDevice(), color.getRed(), color.getGreen(), color.getBlue());
		gc.setForeground(aColor);
		Geometry geometry = getGeometry();
		gc.drawRectangle(geometry.getX(), geometry.getY(), geometry.getWidth() + 10, geometry.getHeight());
		gc.drawText(getValue(), geometry.getX() + 3, geometry.getY() + 3);
	}

	@Override
	public void rate(java.awt.Color cor) {
		this.color = cor;
	}

	@Override
	public Class<?> getType() {
		return InputGeometry.class;
	}

	@Override
	public String getSimpleType() {
		return "Input";
	}

}
