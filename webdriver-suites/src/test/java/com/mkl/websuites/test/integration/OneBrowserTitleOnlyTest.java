package com.mkl.websuites.test.integration;

import com.mkl.websuites.MultiBrowserTestCase;



public class OneBrowserTitleOnlyTest extends MultiBrowserTestCase {


	@Override
	protected void runLocally() {
		
		System.out.println("is run!");
		
		webDriver.get(basePath);
		
		assertEquals("Test server", webDriver.getTitle());
	}

	@Override
	protected String getTestName() {
		return "empty page with title";
	}

}
