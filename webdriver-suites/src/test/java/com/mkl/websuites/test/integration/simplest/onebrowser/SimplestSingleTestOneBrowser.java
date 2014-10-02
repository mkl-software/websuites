package com.mkl.websuites.test.integration.simplest.onebrowser;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.test.core.WebSuitesResultCheck;


public class SimplestSingleTestOneBrowser extends WebSuitesResultCheck {

	
	
	@Test
	public void checkWebResultForOneBrowser() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalTestConfig.Runner.class);
		
		checkRunCount(calculateExpectedRunCount(1, 1) + 1, testResult); // +1 for no-web-suite tests
		
		checkIfNoFailures(testResult);
		
	}
	


	

	
	
}
