package com.mkl.websuites.test.integration.bugs;

import org.junit.Assert;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;

public class TestIE80 extends WebSuiteStandaloneTest {

	
	@WebSuitesConfig(
		browsers = {"ie"},
		host = "google.com",
		browsersConfiguration = BrowsersConfig.class
	)
	public static class Config {}
	
	
	
	@Override
	protected void runLocally() {
		
		// test default behaior - if no http:// prefix then IE doesn regoznize the URL
		goTo("google.com:80");
		Assert.assertFalse(browser.getTitle().contains("Google"));
		
		// test if basePath propagation is fixing this automatically:
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(basePath);
		goTo(basePath);
		Assert.assertTrue(browser.getTitle().contains("Google"));
	}

	@Override
	protected String getTestName() {
		return "IE explicit port 80 on remote sites";
	}

}
