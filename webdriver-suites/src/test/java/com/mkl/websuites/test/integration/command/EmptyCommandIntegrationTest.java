package com.mkl.websuites.test.integration.command;

import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.test.integration.command.EmptyCommandIntegrationTestConfig.LocalConfig;
import com.mkl.websuites.test.integration.command.EmptyCommandIntegrationTestConfig.LocalEmptyCommandIntegrationTest;
import com.mkl.websuites.test.unit.scenario.SampleCommand;


@RunWith(JMockit.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmptyCommandIntegrationTest extends WebSuitesResultCheck {

	@WebSuitesRunner(configurationClass = LocalConfig.class, suite = LocalEmptyCommandIntegrationTest.class)
	public static class LocalRunner  extends WebSuites {}
	
	
	
	
	@Test
	public void verifySampleCommandInvocation(@Mocked final SampleCommand mockedCommand) throws Throwable {
		super.checkWebTestResult();
		new Verifications() {{
			mockedCommand.run();
		}
		};
	}
	
	
	@Override
	protected int defineExpectedRunCount() {
		return 3;
	}

	@Override
	protected Class<?> getRunnerClass() {
		return LocalRunner.class;
	}
	
	
}
