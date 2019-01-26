package net.douglashiura.us.serial;

import java.awt.Color;

public enum Results {

	OK(Color.GREEN),
	ERROR(Color.RED),
	FAIL(Color.BLUE),
	NOT_EXECUTED(Color.BLACK),
	END(Color.BLACK);
	
	private Color color;
	
	private Results(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

}