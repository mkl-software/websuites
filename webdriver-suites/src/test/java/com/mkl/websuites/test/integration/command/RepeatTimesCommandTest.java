package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.By;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuites;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;



class RepeatTimesCommandTestConfig {
	
	

	@Scenarios("src/test/resources/integration/command/repeatTimes.scn")
	public static class LocalUnderlyingRepeatTest extends ScenarioFileTest {}
	
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
	
	
}

public class RepeatTimesCommandTest extends WebSuitesResultCheck {

	
	@WebSuites(configurationClass = LocalConfigForCommandTests.class,
			suite = {RepeatTimesCommandTestConfig.LocalUnderlyingRepeatTest.class,
		RepeatTimesCommandTestConfig.RepeatCheckTest.class})
	public static class LocalRunner  extends WebSuitesRunner {}
	
	
	@Test
	public void testClick() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1 , testResult); // + 1 for repeat result check
		
		checkIfNoFailures(testResult);
		
	}
	


}
