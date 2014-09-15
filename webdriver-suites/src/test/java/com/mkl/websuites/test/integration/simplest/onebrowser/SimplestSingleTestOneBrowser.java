package com.mkl.websuites.test.integration.simplest.onebrowser;

import org.junit.Test;

import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.test.integration.simplest.onebrowser.LocalTestConfig.Runner;


public class SimplestSingleTestOneBrowser extends WebSuitesResultCheck {

	
	
	@Test
	public void checkWebResultForOneBrowser() throws Throwable {
		super.checkWebTestResult();
	}
	
	@Override
	protected Class<Runner> getRunnerClass() {
		return LocalTestConfig.Runner.class;
	}

	@Override
	protected int defineExpectedRunCount() {
		return calculateExpectedRunCount(1, 1) + 1; // +1 for no-web-suite tests
	}

	

	
	
}
