package test.net.douglashiura.sc3n4r10.glue.code.usuid;

import net.douglashiura.us.Fixture;
import test.net.douglashiura.selenium.SeleniumScenario;

@Fixture("Authentication")
public class FixtureAuthentication {

	private SeleniumScenario selenium;

	public FixtureAuthentication() {
		selenium = SeleniumScenario.getInstance();
	}

	public void setEmail(String value) {
		selenium.type("newAccount_email",value);
	}

	public void setPassword(String value) {
		selenium.type("newAccount_password",value);
	}

	public void setUser(String value) {
		selenium.type("newAccount_user",value);
	}
	
	public String getCreate() {
		return selenium.getText("createUser");
	}

	public String getEmail() {
		return  selenium.getPlaceholder("newAccount_email");
	}
	public String getPassword() {
		return  selenium.getPlaceholder("newAccount_password");
	}
	public String getUrl() {
		return  selenium.getUrl();
	}

	public String getUser() {
		return  selenium.getPlaceholder("newAccount_user");
	}

	public String getUserGuide() {
		return selenium.getText("userGuide");
	}
	public String getTitleAuthentication() {
		return selenium.getText("authentication_title");
	}
	public String getEnterLogin() {
		return  selenium.getText("authentication_enter");
	}
	public String getTitleNewAccount() {
		return selenium.getText("newAccount_title");
	}

	public String getPasswordLogin() {
		return  selenium.getPlaceholder("authentication_password");
	}

	
	public String getUserLogin() {
		return  selenium.getPlaceholder("authentication_user");
	}

	public void toProjects(String action) {
		selenium.click("createUser");
	}

	public void toUserGuide(String action) {
	}
}