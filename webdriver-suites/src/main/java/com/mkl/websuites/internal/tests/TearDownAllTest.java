package com.mkl.websuites.internal.tests;

import junit.framework.TestCase;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;

public class TearDownAllTest extends TestCase {

	private Runnable tearDownLogic;


	public TearDownAllTest(Runnable tearDownLogic) {
		this.tearDownLogic = tearDownLogic;
	}
	
	@Override
	public String getName() {
		return "Finalize all tests";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		WebSuites config = WebSuitesConfig.get();
		
		String currentBrowser = WebSuitesRunner.getCurrentlyDefiningBrowser();
		
		if (!config.site().doNotCloseBrowserAtTheEnd() && !currentBrowser.equals("none")) {
			
			ServiceFactory.get(BrowserController.class).getWebDriver().quit();
		}
		
		tearDownLogic.run();
	}
	
}
