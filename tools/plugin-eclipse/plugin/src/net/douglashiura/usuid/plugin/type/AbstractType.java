package net.douglashiura.usuid.plugin.type;

import java.util.UUID;

public abstract class AbstractType {

	private Geometry geometry;
	private String model;
	private String value;
	private UUID id;

	public AbstractType(UUID id, Geometry geometry, String model, String value) {
		this.id = id;
		this.geometry = geometry;
		this.model = model;
		this.value = value;
	}

	public UUID getId() {
		return id;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public String getFixtureName() {
		return model;
	}

	public String getValue() {
		return value;
	}
}
