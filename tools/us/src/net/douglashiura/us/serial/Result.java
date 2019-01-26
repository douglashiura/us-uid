package net.douglashiura.us.serial;

import java.io.Serializable;
import java.util.UUID;

public class Result implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private Results result;
	private String actual;

	public Result(UUID uuid, Results result, String actual) {
		this.uuid = uuid;
		this.result = result;
		this.actual = actual;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Results getResult() {
		return result;
	}

	public String getActual() {
		return actual;
	}

}
