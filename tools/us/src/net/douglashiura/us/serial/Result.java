package net.douglashiura.us.serial;

import java.awt.Color;
import java.io.Serializable;

public class Result implements Serializable {
	public enum Results {
		OK(Color.GREEN), ERRO(Color.RED), FAIL(Color.BLUE), UN_EXECUTED(Color.BLACK), END(Color.BLACK);
		private Color color;

		private Results(Color color) {
			this.color = color;
		}

		public Color getColor() {
			return color;
		}
	}

	private static final long serialVersionUID = 1L;
	private String id;
	private Results result;
	private String actual;

	public Result(String id, Results result, String actual) {
		this.id = id;
		this.result = result;
		this.actual = actual;
	}

	public String getId() {
		return id;
	}

	public Results getResult() {
		return result;
	}

	public String getActual() {
		return actual;
	}
}
