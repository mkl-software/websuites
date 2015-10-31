package com.mkl.websuites.itests.web.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;






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
