package test.net.douglashiura.sc3n4r10.glue.code.usuid;

import net.douglashiura.us.Fixture;
import test.net.douglashiura.selenium.SeleniumScenario;

@Fixture("Projects")
public class FixtureProjects {

	private SeleniumScenario selenium;

	public FixtureProjects() {
		selenium = SeleniumScenario.getInstance();
	}

	public void setName(String value) {

	}

	public String getCreate() {
		return selenium.getText("create");
	}

	public String getEmptyProject() {
		return selenium.getText("emptyProject");
	}

	public String getName() {
		return selenium.getText("name");
	}

	public String getPlaceholderName() {
		return selenium.getPlaceholder("name");
	}

	public String getTitleProject() {
		return selenium.getText("project_title");
	}

	public String getTitleProjects() {
		return selenium.getText("projects_title");
	}

	public String getUrl() {
		return selenium.getUrl();
	}

	public String getUserGuide() {
		return selenium.getText("userGuide");
	}

	public void toProject(String action) {
		if ("Create".equals(action)) {
			selenium.click("create");
		}
	}

	public void toUserGuide(String action) {
		if ("UserGuide".equals(action)) {
			selenium.click("userGuide");
		}
	}

}