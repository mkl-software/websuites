package com.mkl.websuites.test.client;

import com.mkl.websuites.MultiBrowserTestCase;

public class SampleStandaloneTest extends MultiBrowserTestCase {


	@Override
	protected void runLocally() {
		browser.get("http://google.com");
	}

	@Override
	protected String getTestName() {
		return "user defined test";
	}

	

}
