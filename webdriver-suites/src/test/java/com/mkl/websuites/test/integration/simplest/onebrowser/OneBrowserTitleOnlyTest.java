package com.mkl.websuites.test.integration.simplest.onebrowser;

import com.mkl.websuites.MultiBrowserTestCase;



public class OneBrowserTitleOnlyTest extends MultiBrowserTestCase {


	@Override
	protected void runLocally() {
		
		browser.get(basePath);
		
		assertEquals("Test server", browser.getTitle());
	}

	@Override
	protected String getTestName() {
		return "empty page with title";
	}

}
