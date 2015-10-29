package com.mkl.websuites.test.integration.command;

import static org.assertj.core.api.Assertions.assertThat;
import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.config.ScenarioFile;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.test.BrowsersForWebTests;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.test.unit.scenario.cmd.SampleCommand;


	


	public class EmptyCommandIntegrationTest extends WebSuitesResultCheck {

	@WebSuites(
			scenarios = @ScenarioFile("src/test/resources/unit/scenarios/running/oneSampleCommand.scn"),
			browsers = "html")
	public static class LocalRunner  extends WebSuitesRunner {}
	
	
	
	@Test
	public void verifySampleCommandInvocation() throws Throwable {
		
		// not a perfect verification, would pass also if SampleCommand constructor wasn't
		// invoked at all... But in this when argument string is modified, the test doesn't pass
		// it's OK for testing purposes
		new MockUp<SampleCommand>() {
			@Mock
			public void $init(String argument) {
				assertThat(argument).isEqualTo("from scenario file");
			}
		};
		
		Result testResult = super.checkWebTestResult(LocalRunner.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
	}
	
	

}

