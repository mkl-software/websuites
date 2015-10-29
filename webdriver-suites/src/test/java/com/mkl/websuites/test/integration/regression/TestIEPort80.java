package com.mkl.websuites.test.integration.regression;

import org.junit.Assert;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.config.SiteConfig;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.tests.WebSuiteStandaloneTest;

public class TestIEPort80 extends WebSuiteStandaloneTest {

	
	@WebSuites(
		browsers = {"ie"},
		browserResusableConfiguration = BrowsersConfig.class,
		site = @SiteConfig(host = "google.com")
	)
	public static class LocalRunner extends WebSuitesRunner {}
	
	
	
	@Override
	protected void runLocally() {
		
		// test default behaior - if no http:// prefix then IE doesn regoznize the URL
		goTo("google.com:80");
		Assert.assertFalse(browser.getTitle().contains("Google"));
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// test if basePath propagation is fixing this automatically:
		System.out.println(basePath);
		goTo(basePath);
		Assert.assertTrue(browser.getTitle().contains("Google"));
	}

	@Override
	protected String getTestName() {
		return "IE explicit port 80 on remote sites";
	}

}
