package net.douglashiura.us.serial;

import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {


	private static final long serialVersionUID = 1L;
	private UUID uuid;
	private Interaction target;
	private Interaction source;

	public Transaction(UUID uuid, Interaction source, Interaction target) {
		this.uuid = uuid;
		this.source = source;
		this.target = target;
	}

	public Interaction getTarget() {
		return target;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Interaction findFist() {
		return source;
	}

}
