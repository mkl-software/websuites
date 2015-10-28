package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuites;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;






public class SelectCommandsTest extends WebSuitesResultCheck {

	
	@Scenarios("src/test/resources/integration/command/selectCommands.scn")
	public static class SelectCommandsScenarioFile extends ScenarioFileTest {}
	
	
	@WebSuites(configurationClass = LocalConfigForCommandTests.class,
			suite = SelectCommandsScenarioFile.class)
	public static class LocalRunnerSelectCommands  extends WebSuitesRunner {}
	
	
	
	
	
	@Test
	public void shouldRunSelectCommandsAllPassing() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerSelectCommands.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
		checkIfNoFailures(testResult);
	}
	
	

}
