package com.mkl.websuites.test.integration.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;

public class CheckTitleTest extends WebSuitesResultCheck {

	
	@Scenarios("src/test/resources/integration/command/check/titleOK.scn")
	public static class ScenarioWithTitleCheckResultOK extends ScenarioFileTest {}
	
	@Scenarios("src/test/resources/integration/command/check/checkTitleFail.scn")
	public static class ScenarioWithTitleCheckResultFail extends ScenarioFileTest {}

	@WebSuitesRunner(configurationClass = LocalConfigForCommandTests.class,
			suite = CheckTitleTest.ScenarioWithTitleCheckResultOK.class)
	public static class LocalRunnerForTitleCheckOK  extends WebSuites {}
	
	
	@WebSuitesRunner(configurationClass = LocalConfigForCommandTests.class,
			suite = CheckTitleTest.ScenarioWithTitleCheckResultFail.class)
	public static class LocalRunnerForTitleCheckFail  extends WebSuites {}
	
	
	
	@Test
	public void shouldTitleBeOK() throws Throwable {
		//when
		Result testResult = checkWebTestResult(LocalRunnerForTitleCheckOK.class);
		//then
		checkRunCount(CORRECT_TEST_COUNT_FOR_SINGLE_TEST, testResult);
		
		checkIfNoFailures(testResult);
	}
	
	
	@Test
	public void shouldTitleBeFailed() throws Throwable {
		//when
		Result testResult = checkWebTestResult(LocalRunnerForTitleCheckFail.class);
		//then
		checkRunCount(CORRECT_TEST_COUNT_FOR_SINGLE_TEST, testResult);
		
		assertThat(testResult.getFailureCount()).isEqualTo(1);
		assertThat(testResult.getFailures().get(0).getMessage()).contains("Not expected title");
	}
	
}
