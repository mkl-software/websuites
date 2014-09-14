package com.mkl.websuites.test.integration.simplest.twobrowser;

import com.mkl.websuites.MultiBrowserTestCase;



public class TwoBrowserTitleOnlyTest extends MultiBrowserTestCase {


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
