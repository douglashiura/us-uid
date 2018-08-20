package net.douglashiura.usuid.plugin.type;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public class Transaction implements Rateable {
	private java.awt.Color cor = java.awt.Color.BLACK;
	private Interaction target;
	private Interaction source;
	private String id;

	public Transaction(String id, Interaction source, Interaction target) {
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public Interaction getTarget() {
		return target;
	}

	public void draw(GC gc) {
		Color saida = new Color(gc.getDevice(), cor.getRed(), cor.getGreen(), cor.getBlue());
		gc.setForeground(saida);
		Interaction anotherState = target;
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
	public String getFixture() {
		return String.format("to%s", target.getFixture());
	}

	@Override
	public String getValue() {
		return "";
	}
	@Override
	public Class<?> getType() {
		return Transaction.class;
	}
}
