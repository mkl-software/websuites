package com.mkl.websuites.internal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.TestCase;

public class SwitchBrowserTest extends TestCase {

	private String browserName;
	
	public SwitchBrowserTest(String browser) {
		this.browserName = browser;
	}

	
	
	@Override
	public String getName() {
		return "Switching to browser: " + browserName;
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		BrowserController browserController = BrowserController.getInstance();
		
		WebDriver driver = browserController.getWebDriver();
		
		// check if first test, then nothing to close yet
		if (driver != null) {
			driver.quit();
		}
		
		browserController.setWebDriver(new FirefoxDriver());
	}
	
}
