package test.net.douglashiura.leb.uid.scenario.glue.code;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.douglashiura.leb.uid.scenario.Interaction;
import net.douglashiura.leb.uid.scenario.Transaction;
import net.douglashiura.leb.uid.scenario.glue.code.ExtractInteractions;

public class TestExtractInteractionsOfScenarios {
	private Interaction scenario;
	private Interaction scenario2;
	@Before
	public void setUp() {
		scenario = new Interaction("", "EightPuzzle");
		scenario2 = new Interaction("", "EightPuzzle");
		scenario2.setTransaction(new Transaction("", scenario2, scenario));
	}

	@Test
	public void anInteraction() throws Exception {
		List<Interaction> interactions = ExtractInteractions.from(Arrays.asList(scenario));
		assertEquals(1, interactions.size());
		assertEquals(scenario, interactions.get(0));
	}
	@Test
	public void threeInteraction() throws Exception {
		List<Interaction> interactions = ExtractInteractions.from(Arrays.asList(scenario));
		assertEquals(1, interactions.size());
		assertEquals(scenario, interactions.get(0));
	}

}
