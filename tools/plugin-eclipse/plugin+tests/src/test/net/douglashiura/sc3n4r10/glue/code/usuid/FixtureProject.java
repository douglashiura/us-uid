package test.net.douglashiura.sc3n4r10.glue.code.usuid;

import net.douglashiura.us.Fixture;
import test.net.douglashiura.selenium.SeleniumScenario;

@Fixture("Project")
public class FixtureProject {
	private SeleniumScenario selenium;

	public FixtureProject() {
		selenium = SeleniumScenario.getInstance();
	}

	public void setName(String value) {
		selenium.type("name", value);
	}

	public void setPackage(String value) {
		selenium.type("package", value);
	}

	public void setScenarioInput1(String value) {
		selenium.type("input", value);
	}

	public String getClone1() {
		return null;
	}

	public String getCopy() {
		return null;
	}

	public String getDelete1() {
		return null;
	}

	public String getEmptyScenario() {
		return null;
	}

	public String getMemory() {
		return null;
	}

	public String getName() {
		return null;
	}

	public String getNewScenario() {
		return null;
	}

	public String getPackage() {
		return null;
	}

	public String getPlaceholderName() {
		return null;
	}

	public String getPlaceholderPackage() {
		return null;
	}

	public String getRename1() {
		return null;
	}

	public String getScenario1() {
		return null;
	}

	public String getScenario2() {
		return null;
	}

	public String getScenarioInput1() {
		return null;
	}

	public String getTitleNewScenario() {
		return null;
	}

	public String getTitleUserScenario() {
		return null;
	}

	public String getUrl() {
		return null;
	}

	public String getUrlClone() {
		return null;
	}

	public String getUrlCloneTitle() {
		return null;
	}

	public void toProject(String action) {
	}

	public void toScenario(String action) {
	}
}