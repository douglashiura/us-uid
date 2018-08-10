package test.net.douglashiura.us;

import static org.junit.Assert.*;

import org.junit.Test;

import net.douglashiura.us.Annotations;
import net.douglashiura.us.Fixture;
@Fixture("Exemplo")
public class TestAnnotations {

	@Test
	public void selfAnnotation() throws Exception {
		assertNotNull(Annotations.getFixture("Exemplo"));
	}

}
