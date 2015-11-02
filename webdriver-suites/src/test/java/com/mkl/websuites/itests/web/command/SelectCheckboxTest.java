package com.mkl.websuites.itests.web.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;






public class SelectCheckboxTest extends WebSuitesResultCheck {

	
	
	
	@WebSuites(
		browsers = "${env.testBrowser}",
		scenarios = @ScenarioFile("src/test/resources/integration/command/select/selectCheckbox.scn"),
		browserResusableConfiguration = BrowsersConfig.class
	)
	public static class LocalRunnerSelectCheckboxCommands  extends WebSuitesRunner {}
	
	
	
	
	@Test
	public void shouldRunSelectCheckboxCommandsAllPassing() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalRunnerSelectCheckboxCommands.class);
		
		checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);
		
		checkIfNoFailures(testResult);
	}
	
	

}
