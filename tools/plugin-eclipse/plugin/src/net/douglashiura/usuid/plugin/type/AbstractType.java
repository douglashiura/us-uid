package net.douglashiura.usuid.plugin.type;

public abstract class AbstractType {

	private Geometry geometry;
	private String model;
	private String value;
	private String id;

	public AbstractType(String id, Geometry geometry, String model, String value) {
		this.id = id;
		this.geometry = geometry;
		this.model = model;
		this.value = value;
	}

	public String getId() {
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
