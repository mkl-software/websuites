package com.mkl.websuites.internal.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.mkl.websuites.Browser;
import com.mkl.websuites.Browser.BrowserType;
import com.mkl.websuites.BrowsersConfiguration;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.BrowserController;



@Slf4j
public class BrowserControllerImpl implements BrowserController {

	
	
	protected Queue<String> browsersToRun = new LinkedBlockingQueue<String>();

	protected WebDriver webDriver;
	
	private boolean firstBrowser = true;
	
	private String localBrowserNameForTestInit;

	protected Map<String, Class<?>> driverClassMap = new HashMap<String, Class<?>>();

	protected Map<String, String> browserDisplayNameMap = new HashMap<String, String>();
	
	
	
	protected BrowserControllerImpl() {}
	
	private static BrowserController instance = new BrowserControllerImpl();
	
	public static BrowserController getInstance() {
		return instance;
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#initializeBrowsersEnvironment(com.mkl.websuites.WebSuitesConfig)
	 */
	@Override
	public void initializeBrowsersEnvironment(WebSuitesConfig config) {
		
		BrowsersConfiguration browsersConfiguration =
				config.browsersConfiguration().getAnnotation(BrowsersConfiguration.class);
		
		if (browsersConfiguration == null) {
			
			log.error("empty browser configuration");
			throw new WebSuitesException("Empty browser configuration, please fill properly "
					+ "the BrowserConfiguration section in the configuration");
		}
		
		Browser[] browsers = browsersConfiguration.browsers();
		
		// populate default FF browser in case it's not existing int the browser config annotation:
		driverClassMap.put("ff", FirefoxDriver.class);
		browserDisplayNameMap.put("ff", "Firefox");
		// if it's existing it will overwritten in next loop
		
		for (Browser browser : browsers) {
			
			browserDisplayNameMap.put(browser.localId(), browser.displayName());
			
			if (browser.browserType() == BrowserType.INTERNET_EXPLORER) {
				
				configureBrowser("webdriver.ie.driver", browser, InternetExplorerDriver.class);
			}
			
			if (browser.browserType() == BrowserType.CHROME) {
				
				configureBrowser("webdriver.chrome.driver", browser, ChromeDriver.class);
			}
			
			if (browser.browserType() == BrowserType.FIREFOX) {
				
				configureBrowser("", browser, FirefoxDriver.class);
			}
		}
		
	}





	protected void configureBrowser(String envKey, Browser browser, Class<?> driverClass) {
		
		if (!envKey.isEmpty()) {
			System.setProperty(envKey, browser.webDriverPath());
		}
		driverClassMap.put(browser.localId(), driverClass);
	}
	
	
	
	
	
	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#addBrowser(java.lang.String)
	 */
	@Override
	public void addBrowser(String browser) {
		browsersToRun.offer(browser);
		localBrowserNameForTestInit = browser;
	}
	
	
	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#currentBrowser()
	 */
	@Override
	public String currentBrowser() {
		return browsersToRun.peek();
	}
	
	
	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#removeCurrentBrowser()
	 */
	@Override
	public String removeCurrentBrowser() {
		String current = "";
		if (firstBrowser) {
			firstBrowser = false;
		} else {
			current = browsersToRun.poll();
		}
		return current;
	}



	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#getWebDriver()
	 */
	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}


	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#setNextWebDriver()
	 */
	@Override
	public void setNextWebDriver() {
		
		String currentBrowser = currentBrowser();
		
		if (!driverClassMap.containsKey(currentBrowser)) {
			
			log.error("no browser configured for ID: " + currentBrowser);
			throw new WebSuitesException("No browser configured for the ID: '" + currentBrowser +
					"', Please correct your BrowserConfiguration element and fill information for "
					+ "this browser.");
		}
		
		Class<?> driverClass = driverClassMap.get(currentBrowser);
		
		try {
			webDriver = (WebDriver) driverClass.newInstance();
			
		} catch (InstantiationException | IllegalAccessException e) {
			
			log.error("cannot create an instance of Web Driver for [" + currentBrowser +
					"] with class: " + driverClass);
		}
	}


	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#getLocalBrowserNameForTestInit()
	 */
	@Override
	public String getLocalBrowserNameForTestInit() {
		return localBrowserNameForTestInit;
	}


	/* (non-Javadoc)
	 * @see com.mkl.websuites.internal.IBrowserControlle#getBrowserName(java.lang.String)
	 */
	@Override
	public String getBrowserDisplayName(String currentBrowser) {
		
		return browserDisplayNameMap.get(currentBrowser);
	}

}
