package net.douglashiura.leb.uid.scenario;

public abstract class AbstractType {

	private String fixture;
	private String value;
	private String id;

	public AbstractType(String id,String fixture, String value) {
		this.id = id;
		this.fixture = fixture;
		this.value = value;
	}

	public String getFixture() {
		return fixture;
	}

	public String getId() {
		return id;
	}
	
	public String getValue() {
		return value;
	}
	
}
