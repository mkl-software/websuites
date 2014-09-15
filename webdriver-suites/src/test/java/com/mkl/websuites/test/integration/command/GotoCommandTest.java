package com.mkl.websuites.test.integration.command;

import org.junit.Test;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.test.integration.command.GotoCommandTestConfig.LocalCheckTitleTest;
import com.mkl.websuites.test.integration.command.GotoCommandTestConfig.LocalConfig;
import com.mkl.websuites.test.integration.command.GotoCommandTestConfig.LocalGotoCommandTest;


public class GotoCommandTest extends WebSuitesResultCheck {

	@WebSuitesRunner(configurationClass = LocalConfig.class, suite = {LocalGotoCommandTest.class, LocalCheckTitleTest.class})
	public static class LocalRunner  extends WebSuites {}
	
	
	
	
	@Test
	public void verifySampleCommandInvocation() throws Throwable {
		super.checkWebTestResult();
	}
	
	
	@Override
	protected int defineExpectedRunCount() {
		return 3 + 1; // +1 for second test checking title
	}

	@Override
	protected Class<?> getRunnerClass() {
		return LocalRunner.class;
	}
	
	
}
