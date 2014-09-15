package com.mkl.websuites.test.integration.command;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.tests.SingleScenarioFileTest;


@Slf4j
public class GotoCommandTestConfig {
	
	
	@WebSuitesConfig(browsers = {"ff"})
	public static class LocalConfig {}

	public static class LocalGotoCommandTest extends SingleScenarioFileTest {

		@Override
		protected String getScenarioFileLocation() {
			
			return "src/test/resources/integration/command/gotoCommand.scn";
		}
	}
	
	
	public static class LocalCheckTitleTest extends TestCase {
		
		@Override
		public String getName() {
			return "Check title after Goto command";
		}
		
		@Override
		public void runTest() {
			
			String title = ServiceFactory.get(BrowserController.class).getWebDriver().getTitle();
			
			Assert.assertEquals("Google", title);
			
			log.debug("title verified");
		}
	}
}
