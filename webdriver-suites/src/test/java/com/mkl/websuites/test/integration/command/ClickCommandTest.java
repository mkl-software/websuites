package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.Alert;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;



class ClickCommandTestConfig {
	
	

	@Scenarios("src/test/resources/integration/command/clickTest1.scn")
	public static class LocalUnderlyingClickTest extends ScenarioFileTest {}
	
	public static class ClickCheckTest extends WebSuiteStandaloneTest {

		@Override
		protected void runLocally() {
			
			Alert alert = browser.switchTo().alert();
			String text = alert.getText();
			assertEquals("click works", text);
			alert.accept();
		}

		@Override
		protected String getTestName() {
			return "Click with alert";
		}
		
	}
	
	
}

public class ClickCommandTest extends WebSuitesResultCheck {

	
	@WebSuitesRunner(configurationClass = LocalConfigForCommandTests.class,
			suite = {ClickCommandTestConfig.LocalUnderlyingClickTest.class,
		             ClickCommandTestConfig.ClickCheckTest.class})
	public static class LocalRunner  extends WebSuites {}
	
	
	@Test
	public void testClick() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(3 + 1 , testResult); // + 1 for click result check
		
		checkIfNoFailures(testResult);
		
	}
	


}
