package net.douglashiura.us;

import java.util.UUID;

public abstract class AbstractType {

	private UUID uuid;
	private String fixtureName;
	private String value;

	public AbstractType(UUID uuid,String fixtureName, String value) {
		this.uuid = uuid;
		this.fixtureName = fixtureName;
		this.value = value;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public String getFixtureName() {
		return fixtureName;
	}
	
	public String getValue() {
		return value;
	}
	
}
