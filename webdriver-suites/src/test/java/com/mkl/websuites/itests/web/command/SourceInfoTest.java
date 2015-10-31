package com.mkl.websuites.itests.web.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Result;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;

public class SourceInfoTest extends WebSuitesResultCheck {

	
	
	@WebSuites(
			browsers = "none",
			scenarios = @ScenarioFile("src/test/resources/integration/non-web/errorForSourceInfoTest1.scn"))
	public static class LocalRunnerErrorInCommand1  extends WebSuitesRunner {}
	
	
	@WebSuites(
			browsers = {"none"},
			scenarios = @ScenarioFile("src/test/resources/integration/non-web/errorForSourceInfoTest2.scn"))
	public static class LocalRunnerErrorInCommand2  extends WebSuitesRunner {}
	
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	
//	@Test
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
		
		checkRunCount(BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 1, testResult);
		
		assertThat(testResult.getFailureCount()).isEqualTo(1);
		
		String message = testResult.getFailures().get(0).getMessage();
		
		assertThat(message)
			.contains(": 7")
			.contains("I'm failing")
			.contains("errorForSourceInfoTest2.scn");
		
	}
}
