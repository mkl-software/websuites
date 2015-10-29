package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.By;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.config.ScenarioFile;
import com.mkl.websuites.internal.config.TestClass;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;


	

public class RepeatTimesCommandTest extends WebSuitesResultCheck {

	
	public static class RepeatCheckTest extends WebSuiteStandaloneTest {

		@Override
		protected void runLocally() {
			
			String spanText = browser.findElement(By.id("calculationResult")).getText();
			assertEquals("5", spanText);
			String propTestInputTest =
					browser.findElement(By.id("propTestElement")).getAttribute("value");
			assertEquals("12345", propTestInputTest);
		}

		@Override
		protected String getTestName() {
			return "Repeat n times";
		}
		
	}
	
	
	
	@WebSuites(
		browsers = "${env.testBrowser}",
		scenarios = @ScenarioFile("src/test/resources/integration/command/repeatTimes.scn"),
		classes = @TestClass(RepeatCheckTest.class),
		browserResusableConfiguration = BrowsersConfig.class
	)
	public static class LocalRunner  extends WebSuitesRunner {}
	
	
	
	
	
	@Test
	public void testSimpleRepeat() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1 , testResult); // + 1 for repeat result check
		
		checkIfNoFailures(testResult);
		
	}
	


}
