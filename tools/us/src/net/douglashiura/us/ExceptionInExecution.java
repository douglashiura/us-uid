package net.douglashiura.us;

import net.douglashiura.us.serial.Result.Results;

public class ExceptionInExecution extends Exception {

	private static final long serialVersionUID = 1L;
	private String id;
	private Results erro;
	private String message;

	public ExceptionInExecution(String id, Results erro, String message) {
		this.id = id;
		this.erro = erro;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public Results getResult() {
		return erro;
	}

	public String getMensagem() {
		return message;
	}

}
