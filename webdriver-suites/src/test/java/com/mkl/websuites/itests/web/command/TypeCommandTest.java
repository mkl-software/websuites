package com.mkl.websuites.itests.web.command;

import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.Alert;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.TestClass;
import com.mkl.websuites.internal.tests.WebSuiteStandaloneTest;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;



	
	

	
	
	
	

public class TypeCommandTest extends WebSuitesResultCheck {

	
	
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
	
	@WebSuites(
			browsers = "${env.testBrowser}",
			scenarios = @ScenarioFile("src/test/resources/integration/command/typeTextTest1.scn"),
			classes = @TestClass(TypeTextCheckTest.class),
			browserResusableConfiguration = BrowsersConfig.class
		)
	public static class LocalRunner  extends WebSuitesRunner {}
	
	
	
	
	
	
	@Test
	public void testClick() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1, testResult); // +1 for TypeTextCheckTest
		
		checkIfNoFailures(testResult);
	}
	

}
