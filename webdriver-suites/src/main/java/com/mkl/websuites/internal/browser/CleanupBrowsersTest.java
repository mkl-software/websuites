package com.mkl.websuites.internal.browser;

import junit.framework.TestCase;

import com.mkl.websuites.internal.services.ServiceFactory;

public class CleanupBrowsersTest extends TestCase {

	
	@Override
	public String getName() {
		return "Close all browser windows";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		ServiceFactory.get(BrowserController.class).getWebDriver().quit();
	}
	
}
