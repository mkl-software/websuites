package com.mkl.websuites.internal;

import junit.framework.TestCase;

public class CleanupBrowsersTest extends TestCase {

	
	@Override
	public String getName() {
		return "Close all browser windows";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		BrowserController.getInstance().getWebDriver().quit();
	}
	
}
