package net.douglashiura.us;

public class CamelCase {

	public static String transform(String natural) {
		if (natural == null || natural.length() < 1)
			return "";
		String camel = natural.subSequence(0, 1).toString().toUpperCase();
		String cases = natural.substring(1);
		return camel + cases;
	}

	public static String to(String string) {
		return String.format("to%s",transform(string));
	}

	public static String get(String string) {
		return String.format("get%s",transform(string));
	}

	public static String set(String string) {
		return String.format("set%s",transform(string));
	}

}
