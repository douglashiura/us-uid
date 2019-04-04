package test.net.douglashiura.sc3n4r10.glue.code.usuid;

import net.douglashiura.us.Fixture;
import test.net.douglashiura.selenium.SeleniumScenario;

@Fixture("Project")
public class FixtureProject {
	private SeleniumScenario selenium;

	public FixtureProject() throws InterruptedException {
		selenium = SeleniumScenario.getInstance();
	}

	public void setName(String value) {
		selenium.type("scenario", value);
	}

	public void setPackage(String value) {
		selenium.type("folder", value);
	}

	public void setScenarioInput(String value) {
		selenium.clean("inputFunctions");
		selenium.type("inputFunctions", value);
	}

	public String getClone() {
		return selenium.getText("clone");
	}

	public String getDelete() {
		return selenium.getText("delete");
	}

	public String getRename() {
		return selenium.getText("rename");
	}

	public String getEmptyScenario() {
		return selenium.getText("empty_scenario");
	}

	public String getName() {
		return selenium.getText("name");
	}

	public String getNewScenario() {
		return selenium.getText("new_scenario");
	}

	public String getPackage() {
		return selenium.getText("package");
	}

	public String getPlaceholderName() {
		return selenium.getPlaceholder("scenario");
	}

	public String getPlaceholderPackage() {
		return selenium.getPlaceholder("folder");
	}

	public String getScenario1() throws InterruptedException {
		return selenium.getText("scenario_1");
	}

	public String getScenario2() throws InterruptedException {
		return selenium.getText("scenario_2");
	}

	public String getScenarioInput() {
		return selenium.getValue("inputFunctions");
	}

	public String getTitleNewScenario() {
		return selenium.getText("new_scenario_title");
	}

	public String getTitleUserScenario() {
		return selenium.getText("project_title");
	}

	public String getUrl() {
		return selenium.getUrl();
	}

	public void toProject(String action) throws InterruptedException {
		if ("MouseOn(scenario:1)".equals(action)) {
			selenium.onMouse("new_scenario");
			selenium.onMouse("scenario_1");
			Thread.sleep(200);
		} else if ("MouseOn(scenario:2)".equals(action)) {
			selenium.onMouse("scenario_2");
		} else if ("Delete(senario:1)".equals(action)) {
			selenium.click("delete");
		} else if ("Rename(scenario:1)".equals(action)) {
			selenium.click("rename");
		} else if ("Clone(scenario:1)".equals(action)) {
			selenium.click("clone");
		}

	}

	public void toScenario(String action) {
		if (action.equals("New scenario")) {
			selenium.click("new_scenario");
		} else if ("Click(scenario:1)".equals(action)) {
			selenium.click("scenario_1");
		} else if ("Click(scenario:2)".equals(action)) {
			selenium.click("scenario_2");
		}
	}
}