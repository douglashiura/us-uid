package test.net.douglashiura.leb.uid.scenario.measures;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import net.douglashiura.leb.uid.scenario.glue.code.GetAllScenariosOfAPath;
import net.douglashiura.leb.uid.scenario.measures.FixtureSharedMeasures;
import test.net.douglashiura.leb.uid.scenario.glue.code.use_case.experiment1.UserAndHelper;

public class TesteFixtureSharedMeasures {

	public static void main(String[] args) throws URISyntaxException, IOException {
		File file = new File(UserAndHelper.class.getResource("cadastro.us").toURI());
		File directory = new File(file.getAbsolutePath().replace("cadastro.us", ""));
		GetAllScenariosOfAPath scenarios = new GetAllScenariosOfAPath(directory);
		FixtureSharedMeasures measures = new FixtureSharedMeasures(scenarios.getAll());
		System.out.print(measures.toString());
	}


//	public static void main(String[] args) throws URISyntaxException, IOException {
//		File file = new File(UserOnly.class.getResource("julia/AcessoContato.us").toURI());
//		File directory = new File(file.getAbsolutePath().replace("julia/AcessoContato.us", ""));
//		GetAllScenariosOfAPath scenarios = new GetAllScenariosOfAPath(directory);
//		FixtureSharedMeasures measures = new FixtureSharedMeasures(scenarios.getAll());
//		System.out.print(measures.toString());
//	}
//
}
