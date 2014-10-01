package com.mkl.websuites.test.integration.command;

import org.junit.Test;
import org.openqa.selenium.Alert;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.test.core.WebSuitesResultCheck;
import com.mkl.websuites.tests.ScenarioFiles;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;



class ClickCommandTestConfig {
	
	

	@ScenarioFiles("src/test/resources/integration/command/clickTest1.scn")
	public static class LocalUnderlyingGotoTest extends ScenarioFileTest {}
	
	public static class ClickCheckTest extends WebSuiteStandaloneTest {

		@Override
		protected void runLocally() {
			
			Alert alert = browser.switchTo().alert();
			String text = alert.getText();
			assertEquals("click works", text);
			alert.accept();
		}

		@Override
		protected String getTestName() {
			return "Click with alert";
		}
		
	}
	
	
}

public class ClickCommandTest extends WebSuitesResultCheck {

	
	@WebSuitesRunner(configurationClass = LocalConfig.class,
			suite = {ClickCommandTestConfig.LocalUnderlyingGotoTest.class,
		             ClickCommandTestConfig.ClickCheckTest.class})
	public static class LocalRunner  extends WebSuites {}
	
	
	@Test
	public void testClick() throws Throwable {
		super.checkWebTestResult();
	}
	
	@Override
	protected int defineExpectedRunCount() {
		return 3 + 1; // + 1 for click result check
	}

	@Override
	protected Class<?> getRunnerClass() {
		return LocalRunner.class;
	}

}
