package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.Alert;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.TestClass;
import com.mkl.websuites.internal.tests.WebSuiteStandaloneTest;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.test.core.WebSuitesResultCheck;



class ClickCommandTestConfig {
	
	

	
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

	
	@WebSuites(
		browsers = "${env.testBrowser}",
		scenarios = @ScenarioFile("src/test/resources/integration/command/clickTest1.scn"),
		classes = @TestClass(ClickCommandTestConfig.ClickCheckTest.class),
		browserResusableConfiguration = BrowsersConfig.class
	)
	public static class LocalRunner  extends WebSuitesRunner {}
	
	
	@Test
	public void testClick() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1 , testResult); // + 1 for click result check
		
		checkIfNoFailures(testResult);
		
	}
	


}
