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
		selenium.type("project_name", value);
	}

	public String getCreate() {
		return selenium.getText("project_create");
	}

	public String getSc3n4r10() {
		return selenium.getText("tool");
	}

	public String getLogout() {
		return selenium.getText("logout");
	}

	public String getUser() {
		return selenium.getText("user");
	}

	public String getEmptyProject() {
		return selenium.getText("empty_projects");
	}

	public String getName() {
		return selenium.getText("name_label");
	}

	public String getPlaceholderName() {
		return selenium.getPlaceholder("project_name");
	}

	public String getTitleProject() {
		return selenium.getText("project_title");
	}

	public String getTitleProjects() {
		return selenium.getText("projects_title");
	}

	public String getProject1() {
		return selenium.getText("project_1");
	}
	
	public String getUrl() {
		return selenium.getUrl();
	}

	public String getUserGuide() {
		return selenium.getText("userGuide");
	}

	public void toProject(String action) {
		if ("Create".equals(action)) {
			selenium.click("project_create");
		}
	}

	public void toUserGuide(String action) {
		if ("UserGuide".equals(action)) {
			selenium.click("userGuide");
		}
	}
	
	public void toProjects(String action) throws InterruptedException {
		if ("/douglashiura".equals(action)) {
			selenium.click("user");
		}
	}
	

	public void toAuthentication(String action)  {
		if ("Logout".equals(action)) {
			selenium.click("logout");
		}
	}

}