package com.mkl.websuites.internal.browser;

import junit.framework.TestCase;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.internal.ConfigurationManager;
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
		
		WebSuitesConfig config = ServiceFactory.get(ConfigurationManager.class).getConfiguration();
		
		String currentBrowser = WebSuites.getCurrentlyDefiningBrowser();
		
		if (!config.doNotCloseBrowserAtTheEnd() && !currentBrowser.equals("none")) {
			
			ServiceFactory.get(BrowserController.class).getWebDriver().quit();
		}
		
		tearDownLogic.run();
	}
	
}
