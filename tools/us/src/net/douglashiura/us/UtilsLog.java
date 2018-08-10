package net.douglashiura.us;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UtilsLog {

	public static String toString(Exception e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream s = new PrintStream(out);
		e.printStackTrace(s);
		return out.toString();
	}

}
