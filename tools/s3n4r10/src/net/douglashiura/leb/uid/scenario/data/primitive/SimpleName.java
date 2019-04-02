package net.douglashiura.leb.uid.scenario.data.primitive;

import java.util.regex.Pattern;

public class SimpleName {

	private static final String USER = "^[\\w-]+";
	public static final Pattern USERNAME = Pattern.compile(USER + "$");

	private String name;

	public  SimpleName(String name) throws UsernameNullException, UsernameEmptyException, UsernameBiggerThat30Exception, UsernameInvalidException  {
		assertNotNull(name);
		this.name = name.trim().toLowerCase();
		assertNotEmpty();
		assertEmailSizeLessThat120Caracters();
		assertSyntacticallyValid();
		 
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		}
		if (objeto == null) {
			return false;
		}
		if (getClass() != objeto.getClass()) {
			return false;
		}
		SimpleName outro = (SimpleName) objeto;
		if (name == null) {
			if (outro.name != null) {
				return false;
			}
		} else if (!name.equals(outro.name)) {
			return false;
		}
		return true;
	}

	private void assertSyntacticallyValid() throws UsernameInvalidException {
		if (!USERNAME.matcher(name).matches()) {
			throw new UsernameInvalidException();
		}
	}

	private void assertEmailSizeLessThat120Caracters() throws UsernameBiggerThat30Exception {
		if (name.length() > UsernameBiggerThat30Exception.MAX) {
			throw new UsernameBiggerThat30Exception();
		}
	}

	private void assertNotEmpty() throws UsernameEmptyException {
		if (name.isEmpty()) {
			throw new UsernameEmptyException();
		}
	}

	private void assertNotNull(String email) throws UsernameNullException {
		if (email == null) {
			throw new UsernameNullException();
		}
	}

	public String getName() {
		return name;
	}
}
