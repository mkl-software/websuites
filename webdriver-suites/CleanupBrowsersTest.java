package com.mkl.websuites.internal;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;

import junit.framework.TestCase;

public class CleanupBrowsersTest extends TestCase {

	
	@Override
	public String getName() {
		return "Close remaining browser windows";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		ServiceFactory.get(BrowserController.class).getWebDriver().quit();
	}
	
}
