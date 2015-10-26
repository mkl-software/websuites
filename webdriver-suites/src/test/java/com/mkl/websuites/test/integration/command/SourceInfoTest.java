package com.mkl.websuites.test.integration.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Result;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.client.browserless.NoBrowserConfig;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;

public class SourceInfoTest extends WebSuitesResultCheck {

	
	@Scenarios("src/test/resources/integration/non-web/errorForSourceInfoTest1.scn")
	public static class ErrorInCommandScenario1 extends ScenarioFileTest {}
	
	@Scenarios("src/test/resources/integration/non-web/errorForSourceInfoTest2.scn")
	public static class ErrorInCommandScenario2 extends ScenarioFileTest {}
	
	
	@WebSuitesRunner(configurationClass = NoBrowserConfig.class,
			suite = ErrorInCommandScenario1.class)
	public static class LocalRunnerErrorInCommand1  extends WebSuites {}
	
	
	@WebSuitesRunner(configurationClass = NoBrowserConfig.class,
			suite = ErrorInCommandScenario2.class)
	public static class LocalRunnerErrorInCommand2  extends WebSuites {}
	
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	
	@Test
	public void shouldCatchErrorFromCommandBuilderWithSourceInfo() throws Throwable {
		
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("14")
			.hasMessageContaining("here error")
			.hasMessageContaining("errorForSourceInfoTest1.scn");
		
		super.checkWebTestResult(LocalRunnerErrorInCommand1.class);
	}
	
	
	
	
	@Test
	public void shouldCatchErrorFromCommandBodyWithSourceInfo() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerErrorInCommand2.class);
		
		checkRunCount(1, testResult); // 1 because it's browserless config
		
		assertThat(testResult.getFailureCount()).isEqualTo(1);
		
		String message = testResult.getFailures().get(0).getMessage();
		
		assertThat(message)
			.contains(": 7")
			.contains("I'm failing")
			.contains("errorForSourceInfoTest2.scn");
		
	}
}
