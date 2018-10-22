package net.douglashiura.us;

import java.util.UUID;

public class Transaction {

	private UUID uuid;
	private Interaction target;

	public Transaction(UUID uuid, Interaction source, Interaction target) {
		this.uuid = uuid;
		this.target = target;
	}

	public Interaction getTarget() {
		return target;
	}

	public UUID getUuid() {
		return uuid;
	}

}
