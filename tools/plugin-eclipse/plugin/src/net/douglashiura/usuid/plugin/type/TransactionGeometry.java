package net.douglashiura.usuid.plugin.type;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public class TransactionGeometry implements Rateable {
	private java.awt.Color color;
	private List<InteractionGeometry> targets;
	private InteractionGeometry source;
	private UUID id;

	public TransactionGeometry(UUID id, InteractionGeometry source) {
		this.id = id;
		this.source = source;
		this.targets = new ArrayList<>();
		color = java.awt.Color.BLACK;
	}

	public void addTarget(InteractionGeometry target) {
		targets.add(target);
	}

	public List<InteractionGeometry> getTargets() {
		return targets;
	}

	public void draw(GC gc) {
		Color aColor = new Color(gc.getDevice(), color.getRed(), color.getGreen(), color.getBlue());
		gc.setForeground(aColor);
		for (InteractionGeometry targetState : targets) {
			drawArrow(gc, targetState);
		}
	}

	private void drawArrow(GC gc, InteractionGeometry anotherState) {
		Geometry aGeometry = source.getGeometry();
		Geometry anotherGeometry = anotherState.getGeometry();
		int anotherY = anotherGeometry.getY() + anotherGeometry.getHeight();
		int anotherX = meioX(anotherGeometry);
		gc.drawLine(meioX(aGeometry), aGeometry.getY(), anotherX, anotherY);
		gc.drawPolygon(new int[] { anotherX, anotherY, anotherX + 10, anotherY + 15, anotherX - 10, anotherY + 15 });
	}

	private int meioX(Geometry aGeometry) {
		return aGeometry.getX() + aGeometry.getWidth() / 2;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public void rate(java.awt.Color cor) {
		this.color = cor;
	}

	@Override
	public String getFixtureName() {
		return String.format("to%s", "");
	}

	@Override
	public String getValue() {
		return "";
	}

	@Override
	public Class<?> getType() {
		return TransactionGeometry.class;
	}
}
