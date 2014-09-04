package com.mkl.websuites.internal;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;


@Slf4j
public class SwitchBrowserTest extends TestCase {

	private String browserName;
	
	public SwitchBrowserTest(String browser) {
		this.browserName = browser;
	}

	
	
	@Override
	public String getName() {
		
		String displayName = BrowserController.getInstance().getBrowserName(browserName);
		
		// no browser configured for this ID
		if (displayName == null) {
			displayName = "Not configured!";
		}
		
		return "Switching to browser: " + displayName;
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		BrowserController browserController = BrowserController.getInstance();
		
		WebDriver driver = browserController.getWebDriver();
		
		// check if first test, then nothing to close yet, otherwise close:
		if (driver != null) {
			driver.quit();
		}
		
		String closedBrowser = browserController.removeCurrentBrowser();
		
		log.debug("removing browser [ " + closedBrowser + "] from list of browsers to run");
		
		browserController.setNextWebDriver();
	}
	
}
