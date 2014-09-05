package com.mkl.websuites.test.integration.simplest.onebrowser;

import com.mkl.websuites.MultiBrowserTestCase;



public class OneBrowserTitleOnlyTest extends MultiBrowserTestCase {


	@Override
	protected void runLocally() {
		
		webDriver.get(basePath);
		
		assertEquals("Test server", webDriver.getTitle());
	}

	@Override
	protected String getTestName() {
		return "empty page with title";
	}

}