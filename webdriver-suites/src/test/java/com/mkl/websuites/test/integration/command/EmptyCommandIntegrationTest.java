package com.mkl.websuites.test.integration.command;

import mockit.Mocked;
import mockit.Verifications;

import org.junit.Test;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.test.integration.command.EmptyCommandIntegrationTestConfig.LocalEmptyCommandIntegrationTest;
import com.mkl.websuites.test.unit.scenario.SampleCommand;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.ScenarioFiles;


class EmptyCommandIntegrationTestConfig {
	
	@ScenarioFiles("src/test/resources/unit/scenarios/running/oneSampleCommand.scn")
	public static class LocalEmptyCommandIntegrationTest extends ScenarioFileTest {}
}


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

