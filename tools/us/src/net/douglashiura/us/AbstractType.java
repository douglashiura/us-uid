package net.douglashiura.us;

public abstract class AbstractType {

	private String model;
	private String value;
	private String id;

	public AbstractType(String id,String model, String value) {
		this.id = id;
		this.model = model;
		this.value = value;
	}

	public String getModel() {
		return model;
	}

	public String getId() {
		return id;
	}
	
	public String getValue() {
		return value;
	}
}
