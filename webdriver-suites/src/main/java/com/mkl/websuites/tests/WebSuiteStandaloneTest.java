package com.mkl.websuites.tests;

import com.mkl.websuites.MultiBrowserTestCase;

public abstract class WebSuiteStandaloneTest extends MultiBrowserTestCase {

	
	protected void goTo(String address) {
		browser.get(address);
	}

}
