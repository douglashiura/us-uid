package tests.br.ufsc.leb.adangomes.us.serial;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import net.douglashiura.us.serial.Result;
import net.douglashiura.us.serial.Results;

public class ResultTest {

	@Test
	public void createResult_ok() throws Exception {
		UUID uuid = UUID.randomUUID();
		Result resulta = new Result(uuid, Results.OK, "ok message");
		assertEquals(uuid, resulta.getUuid());
		assertEquals(Results.OK, resulta.getResult());
		assertEquals("ok message", resulta.getActual());
	}
	
	@Test
	public void createResult_fail() throws Exception {
		UUID uuid = UUID.randomUUID();
		Result resulta = new Result(uuid, Results.FAIL, "fail message");
		assertEquals(uuid, resulta.getUuid());
		assertEquals(Results.FAIL, resulta.getResult());
		assertEquals("fail message", resulta.getActual());
	}

}
