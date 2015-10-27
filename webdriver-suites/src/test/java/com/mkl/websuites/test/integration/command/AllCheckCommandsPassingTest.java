package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;






public class AllCheckCommandsPassingTest extends WebSuitesResultCheck {

	
	@Scenarios("src/test/resources/integration/command/check/allCheckCommands_passing.scn")
	public static class AllCheckCommandsScenarioFile extends ScenarioFileTest {}
	
	
	@WebSuitesRunner(configurationClass = LocalConfigForCommandTests.class,
			suite = AllCheckCommandsScenarioFile.class)
	public static class LocalRunnerAllCheckCommandsPassing  extends WebSuites {}
	
	
	
	
	
	@Test
	public void shouldRunAllCheckCommandsAllVersionsAllPassing() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerAllCheckCommandsPassing.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
		checkIfNoFailures(testResult);
	}
	
	

}
