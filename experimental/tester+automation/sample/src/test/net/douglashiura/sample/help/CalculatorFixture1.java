package test.net.douglashiura.sample.help;

import net.douglashiura.us.Fixture;

@Fixture("CalculatorFixture1")
public class CalculatorFixture1 {

	public void setCommand(String name) {

	}

	public String getDisplay() throws InterruptedException {
		Thread.sleep(1000);
		return "3";
		
	}

	public void toCalculatorFixture2() {
	}
}