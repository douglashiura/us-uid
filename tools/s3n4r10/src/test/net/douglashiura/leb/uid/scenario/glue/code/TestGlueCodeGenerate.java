package test.net.douglashiura.leb.uid.scenario.glue.code;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import net.douglashiura.leb.uid.scenario.glue.code.GlueCodeGenerate;
import test.net.douglashiura.leb.uid.scenario.measures.uniform.TestUniformValues;

public class TestGlueCodeGenerate {
	@Test
	public void generate() throws Exception {
		File file = new File(TestUniformValues.class.getResource("teste.us").toURI());
		File directory = new File(file.getAbsolutePath().replace("uniform/teste.us", ""));
		GlueCodeGenerate generate = new GlueCodeGenerate("net.douglas.hiura.test", directory);
		assertTrue(generate.toString().contains("@Fixture"));
	}

	// public static void main(String[] args) throws URISyntaxException, IOException
	// {
	// File file = new File(UserAndHelper.class.getResource("cadastro.us").toURI());
	// File directory = new File(file.getAbsolutePath().replace("cadastro.us", ""));
	// GlueCodeGenerate generate = new
	// GlueCodeGenerate("net.douglashiura.message.experiment1", directory);
	// System.out.print(generate.toString());
	// }
	//
	// // 255
	// // 834
	//
	// public static void main(String[] args) throws URISyntaxException,
	// IOException {
	// File file = new
	// File(UserOnly.class.getResource("julia/AcessoContato.us").toURI());
	// File directory = new
	// File(file.getAbsolutePath().replace("julia/AcessoContato.us", ""));
	// GlueCodeGenerate generate = new
	// GlueCodeGenerate("net.douglashiura.message.experiment2", directory);
	// // System.out.print(generate.toString().split("\n").length);
	// System.out.print(generate.toString());
	// }

}
