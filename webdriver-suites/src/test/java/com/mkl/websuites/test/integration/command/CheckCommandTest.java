package com.mkl.websuites.test.integration.command;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuites;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;



class CheckCommandTestConfig {
	
	@Scenarios("src/test/resources/integration/command/check1.scn")
	public static class ScenarioWithExistingElemByCssId extends ScenarioFileTest {}
	
	@Scenarios("src/test/resources/integration/command/check2.scn")
	public static class ScenarioWithNonExistingElemByCssId extends ScenarioFileTest {}
	
	
	@Scenarios("src/test/resources/integration/command/check3.scn")
	public static class ScenarioForIdParam extends ScenarioFileTest {}
	
	@Scenarios("src/test/resources/integration/command/check4.scn")
	public static class ScenarioForXpathParam extends ScenarioFileTest {}
	
}

public class CheckCommandTest extends WebSuitesResultCheck {

	
	@WebSuites(configurationClass = LocalConfigForCommandTests.class,
			suite = CheckCommandTestConfig.ScenarioWithExistingElemByCssId.class)
	public static class LocalRunnerExistingElemCssId  extends WebSuitesRunner {}
	
	@WebSuites(configurationClass = LocalConfigForCommandTests.class,
			suite = CheckCommandTestConfig.ScenarioWithNonExistingElemByCssId.class)
	public static class LocalRunnerNotExistingElemCssId  extends WebSuitesRunner {}
	
	@WebSuites(configurationClass = LocalConfigForCommandTests.class,
			suite = CheckCommandTestConfig.ScenarioForIdParam.class)
	public static class LocalRunnerForIdParam  extends WebSuitesRunner {}
	
	@WebSuites(configurationClass = LocalConfigForCommandTests.class,
			suite = CheckCommandTestConfig.ScenarioForXpathParam.class)
	public static class LocalRunnerForXpathParam  extends WebSuitesRunner {}
	
	
	
	
	
	@Test
	public void testElementExistingWithCssId() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerExistingElemCssId.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
		checkIfNoFailures(testResult);
	}
	
	
	
	@Test
	public void testElementNotExistingWithCssId() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerNotExistingElemCssId.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

		Assert.assertEquals(1, testResult.getFailureCount());
		
		Failure failure = testResult.getFailures().get(0);
		
		String text = failure.getMessage();
		
		Assert.assertThat("Inproper failure message : " + text,
				text, CoreMatchers.containsString("not_existing"));
	}
	
	
	
	
	@Test
	public void testCheckForIdParam() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerForIdParam.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
		Assert.assertEquals(1, testResult.getFailureCount()); // exactly 1, firtst test OK
		
		Failure failure = testResult.getFailures().get(0);
		
		String text = failure.getMessage();
		
		Assert.assertThat("Inproper failure message : " + text,
				text, CoreMatchers.containsString("not_existing_id"));
	}
	
	
	
	@Test
	public void testCheckForXpathParamNoFailures() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerForXpathParam.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
		checkIfNoFailures(testResult);
		
	}
	


}
