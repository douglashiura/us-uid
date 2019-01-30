package net.douglashiura.us;

import java.util.UUID;

public class Transaction {

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
