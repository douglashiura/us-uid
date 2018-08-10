package net.douglashiura.leb.uid.scenario.data;

public class Element {
	private String usuid;
	private Type type;
	private String value;
	private String fixture;

	public enum Type {
		INPUT, OUTPUT
	}

	public Element(String usuid, Type type, String value, String fixture) {
		this.usuid = usuid;
		this.type = type;
		this.value = value;
		this.fixture = fixture;
	}

	public String getFixture() {
		return fixture;
	}

	public String getUsuid() {
		return usuid;
	}

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

}
