package com.mkl.websuites.test.integration.command;

import org.junit.Test;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.test.integration.command.GotoCommandTestConfig.GotoCheckTest;
import com.mkl.websuites.tests.ScenarioFiles;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;



class GotoCommandTestConfig {
	
	@WebSuitesConfig(browsers = {"ff"})
	public static class LocalConfig {}

	@ScenarioFiles("src/test/resources/integration/command/gotoLocalAddress.scn")
	public static class LocalUnderlyingGotoTest extends ScenarioFileTest {}
	
	public static class GotoCheckTest extends WebSuiteStandaloneTest {

		@Override
		protected void runLocally() {
			assertEquals("Goto test local web", browser.getTitle());
			
		}

		@Override
		protected String getTestName() {
			return "Check goto title";
		}
		
	}
	
	
}

public class GotoCommandTest extends WebSuitesResultCheck {

	
	@WebSuitesRunner(configurationClass = GotoCommandTestConfig.LocalConfig.class,
			suite = {GotoCommandTestConfig.LocalUnderlyingGotoTest.class, GotoCheckTest.class})
	public static class LocalRunner  extends WebSuites {}
	
	
	@Test
	public void testGotoLocalWebAddress() throws Throwable {
		super.checkWebTestResult();
	}
	
	@Override
	protected int defineExpectedRunCount() {
		return 3 + 1; // + 1 for page address check
	}

	@Override
	protected Class<?> getRunnerClass() {
		return LocalRunner.class;
	}

}
