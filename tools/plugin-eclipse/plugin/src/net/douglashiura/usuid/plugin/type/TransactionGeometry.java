package net.douglashiura.usuid.plugin.type;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public class TransactionGeometry implements Rateable {
	private java.awt.Color cor = java.awt.Color.BLACK;
	private List<InteractionGeometry> targets;
	private InteractionGeometry source;
	private String id;

	public TransactionGeometry(String id, InteractionGeometry source) {
		this.id = id;
		this.source = source;
		this.targets = new ArrayList<>();
	}

	public void addTarget(InteractionGeometry target) {
		targets.add(target);
	}

	public List<InteractionGeometry> getTargets() {
		return targets;
	}

	public void draw(GC gc) {
		Color saida = new Color(gc.getDevice(), cor.getRed(), cor.getGreen(), cor.getBlue());
		gc.setForeground(saida);
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

	public String getId() {
		return id;
	}

	@Override
	public void rate(java.awt.Color cor) {
		this.cor = cor;
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
