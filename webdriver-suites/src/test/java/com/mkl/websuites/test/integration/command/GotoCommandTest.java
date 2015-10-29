package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.config.ScenarioFile;
import com.mkl.websuites.internal.config.TestClass;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;


	
	

public class GotoCommandTest extends WebSuitesResultCheck {

	
	
	public static class GotoCheckTest extends WebSuiteStandaloneTest {

		@Override
		protected void runLocally() {
			assertEquals("Goto test local web", browser.getTitle());
			
		}

		@Override
		protected String getTestName() {
			return "Check goto title";
		}
		
	}
	
	@WebSuites(
		browsers = "${env.testBrowser}",
		scenarios = @ScenarioFile("src/test/resources/integration/command/gotoLocalAddress.scn"),
		classes = @TestClass(GotoCheckTest.class),
		browserResusableConfiguration = BrowsersConfig.class
	)
	public static class LocalRunner  extends WebSuitesRunner {}
	
	
	@Test
	public void testGotoLocalWebAddress() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1, testResult); // +1 for LocalUnderlyingGotoTest
		
		checkIfNoFailures(testResult);
	}
	

}
