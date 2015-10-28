package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.Alert;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuites;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;



class TypeCommandTestConfig {
	
	

	@Scenarios("src/test/resources/integration/command/typeTextTest1.scn")
	public static class LocalUnderlyingTypeTest extends ScenarioFileTest {}
	
	public static class TypeTextCheckTest extends WebSuiteStandaloneTest {

		@Override
		protected void runLocally() {
			
			Alert alert = browser.switchTo().alert();
			String text = alert.getText();
			assertEquals("typed text from scn file", text);
			alert.accept();
		}

		@Override
		protected String getTestName() {
			return "Type text and click to check alert text";
		}
		
	}
	
	
}

public class TypeCommandTest extends WebSuitesResultCheck {

	
	@WebSuites(configurationClass = LocalConfigForCommandTests.class,
			suite = {TypeCommandTestConfig.LocalUnderlyingTypeTest.class,
					TypeCommandTestConfig.TypeTextCheckTest.class})
	public static class LocalRunner  extends WebSuitesRunner {}
	
	
	@Test
	public void testClick() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1, testResult); // +1 for TypeTextCheckTest
		
		checkIfNoFailures(testResult);
	}
	

}
