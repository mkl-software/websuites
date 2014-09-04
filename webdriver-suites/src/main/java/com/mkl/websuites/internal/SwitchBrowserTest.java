package com.mkl.websuites.internal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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
		
		String closedBrowser = browserController.removeCurrentBrowser();
		
		log.debug("removing browser [ " + closedBrowser + "] from list of browsers to run");
		
		browserController.setWebDriver(new FirefoxDriver());
	}
	
}
