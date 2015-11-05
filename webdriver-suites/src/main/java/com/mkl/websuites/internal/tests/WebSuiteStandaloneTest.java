package com.mkl.websuites.internal.tests;



/**
 * Convenience API for standalone tests.
 *
 */
public abstract class WebSuiteStandaloneTest extends MultiBrowserTestCase {


    protected void goTo(String address) {
        browser.get(address);
    }

}
