package com.mkl.websuites.test.integration.simplest.twobrowser;

import org.junit.Test;

import com.mkl.websuites.test.core.WebSuitesResultCheck;


public class SimplestSingleTestTwoBrowsers extends WebSuitesResultCheck {


	@Test
	public void checkWebResultForTwoBrowsers() throws Throwable {
		super.checkWebTestResult();
	}
	
	
	@Override
	protected int defineExpectedRunCount() {
		return calculateExpectedRunCount(1, 2);
	}

	@Override
	protected Class<?> getRunnerClass() {
		return LocalTestConfig.Runner.class;
	}
	
}
