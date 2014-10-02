package com.mkl.websuites.test.integration.simplest.twobrowser;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.test.core.WebSuitesResultCheck;


public class SimplestSingleTestTwoBrowsers extends WebSuitesResultCheck {


	@Test
	public void checkWebResultForTwoBrowsers() throws Throwable {
		
		Result testResult = super.checkWebTestResult(LocalTestConfig.Runner.class);
		
		checkRunCount(calculateExpectedRunCount(1, 2), testResult);
		
		checkIfNoFailures(testResult);
	}
	
	

	
}
