package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.test.integration.command.GotoCommandTestConfig.GotoCheckTest;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.ScenarioFiles;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;



class GotoCommandTestConfig {
	

	@ScenarioFiles("src/test/resources/integration/command/gotoLocalAddress.scn")
	public static class LocalUnderlyingGotoTest extends ScenarioFileTest {}
	
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
	
	
}

public class GotoCommandTest extends WebSuitesResultCheck {

	
	@WebSuitesRunner(configurationClass = LocalConfigForCommandTests.class,
			suite = {GotoCommandTestConfig.LocalUnderlyingGotoTest.class, GotoCheckTest.class})
	public static class LocalRunner  extends WebSuites {}
	
	
	@Test
	public void testGotoLocalWebAddress() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(3 + 1, testResult); // +1 for LocalUnderlyingGotoTest
		
		checkIfNoFailures(testResult);
	}
	

}
