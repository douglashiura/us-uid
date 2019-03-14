package net.douglashiura.scenario.plugin.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

import net.douglashiura.us.serial.Results;

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

	@Override
	public void draw(GC gc, Map<UUID, Rateable> only) {
		Color aColor = new Color(gc.getDevice(), color.getRed(), color.getGreen(), color.getBlue());
		java.awt.Color unExecuted = Results.UN_EXECUTED.getColor();
		Color unExecutedColor = new Color(gc.getDevice(), unExecuted.getRed(), unExecuted.getGreen(),
				unExecuted.getBlue());
		for (InteractionGeometry targetState : targets) {
			gc.setForeground(aColor);
			if (only.get(targetState.getId()) != null) {
				drawArrow(gc, targetState);
			} else {
				gc.setForeground(unExecutedColor);
				drawArrow(gc, targetState);
			}
		}
	}

	private void drawArrow(GC gc, InteractionGeometry anotherState) {
		Geometry aGeometry = source.getGeometry();
		Geometry anotherGeometry = anotherState.getGeometry();
		int anotherY = meioY(anotherGeometry);
		int anotherX = anotherGeometry.getX();
		gc.drawLine(aGeometry.getX() + aGeometry.getWidth(), meioY(aGeometry), anotherX-10, anotherY);
		gc.drawPolygon(new int[] { anotherX-10, anotherY-7, anotherX, anotherY, anotherX-10, anotherY+7 });
	}

	private int meioY(Geometry aGeometry) {
		return aGeometry.getY() + aGeometry.getHeight() / 2;
	}

	public UUID getUuid() {
		return id;
	}

	@Override
	public void setColor(java.awt.Color cor) {
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

	@Override
	public Geometry getGeometry() {
		return source.getGeometry();
	}
}
