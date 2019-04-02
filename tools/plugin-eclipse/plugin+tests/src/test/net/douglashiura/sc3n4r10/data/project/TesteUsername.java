package test.net.douglashiura.sc3n4r10.data.project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.data.primitive.SimpleName;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameBiggerThat30Exception;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameEmptyException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameInvalidException;
import net.douglashiura.leb.uid.scenario.data.primitive.UsernameNullException;

public class TesteUsername {
	@Test(expected = UsernameInvalidException.class)
	public void invalid() throws Exception {
		new SimpleName(" a invalid");
	}

	@Test(expected = UsernameInvalidException.class)
	public void invalidWithPlus() throws Exception {
		new SimpleName("douglas+hiura");
	}

	@Test(expected = UsernameInvalidException.class)
	public void invalidWithSpecialCharaters() throws Exception {
		new SimpleName("douglashiurá");
	}

	@Test(expected = UsernameInvalidException.class)
	public void invalidWithSpecialDot() throws Exception {
		new SimpleName("douglas.hiura");
	}

	@Test(expected = UsernameInvalidException.class)
	public void invalidWithSpecialCharaters2() throws Exception {
		new SimpleName("douglaç");
	}

	@Test(expected = UsernameNullException.class)
	public void nullo() throws Exception {
		new SimpleName(null);
	}

	@Test(expected = UsernameEmptyException.class)
	public void empty() throws Exception {
		new SimpleName("");
	}

	@Test(expected = UsernameBiggerThat30Exception.class)
	public void bigger() throws Exception {
		new SimpleName("a23456789a23456789a23456789abcd");
	}

	@Test
	public void email() throws Exception {
		SimpleName douglashiura = new SimpleName("douglashiura");
		assertEquals("douglashiura", douglashiura.getName());
	}

}
