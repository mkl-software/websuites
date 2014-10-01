package com.mkl.websuites.tests;

import com.mkl.websuites.internal.MultiBrowserTestCase;


/**
 * Convenience API for standalone tests.
 *
 */
public abstract class WebSuiteStandaloneTest extends MultiBrowserTestCase {

	
	protected void goTo(String address) {
		browser.get(address);
	}

}
