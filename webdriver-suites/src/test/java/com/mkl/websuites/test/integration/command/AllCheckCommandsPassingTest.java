package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.config.ScenarioFile;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.test.core.WebSuitesResultCheck;






public class AllCheckCommandsPassingTest extends WebSuitesResultCheck {

	
	
	
	@WebSuites(
			browsers = "${env.testBrowser}",
			scenarios = @ScenarioFile("src/test/resources/integration/command/check/allCheckCommands_passing.scn"),
			browserResusableConfiguration = BrowsersConfig.class
		)
	public static class LocalRunnerAllCheckCommandsPassing  extends WebSuitesRunner {}
	
	
	
	
	
	@Test
	public void shouldRunAllCheckCommandsAllVersionsAllPassing() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerAllCheckCommandsPassing.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
		checkIfNoFailures(testResult);
	}
	
	

}
