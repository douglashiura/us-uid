package net.douglashiura.usuid.plugin.type;

import java.awt.Color;

public interface Rateable {

	void rate(Color color);

	String getFixtureName();
	String getValue();

	Class<?> getType();

}