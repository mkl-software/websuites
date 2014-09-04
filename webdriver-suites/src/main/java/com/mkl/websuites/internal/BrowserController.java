package com.mkl.websuites.internal;

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



@Slf4j
public class BrowserController {

	
	private Queue<String> browsersToRun = new LinkedBlockingQueue<String>();

	private WebDriver webDriver;
	
	private boolean firstBrowser = true;
	
	private String localBrowserNameForTestInit;

	private Map<String, Class<?>> driverClassMap = new HashMap<String, Class<?>>();

	private Map<String, String> browserNameMap = new HashMap<String, String>();
	
	
	
	
	private static BrowserController instance = new BrowserController();
	
	public static BrowserController getInstance() {
		return instance;
	}

	
	
	
	
	public void initializeBrowsersEnvironment(WebSuitesConfig config) {
		
		BrowsersConfiguration browsersConfiguration =
				config.browsersConfiguration().getAnnotation(BrowsersConfiguration.class);
		
		if (browsersConfiguration == null) {
			
			log.error("empty browser configuration");
			throw new WebSuitesException("Empty browser configuration, please fill properly "
					+ "the BrowserConfiguration section in the configuration");
		}
		
		Browser[] browsers = browsersConfiguration.browsers();
		
		for (Browser browser : browsers) {
			
			browserNameMap.put(browser.localId(), browser.displayName());
			
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





	private void configureBrowser(String envKey, Browser browser, Class<?> driverClass) {
		
		if (!envKey.isEmpty()) {
			System.setProperty(envKey, browser.webDriverPath());
		}
		driverClassMap.put(browser.localId(), driverClass);
	}
	
	
	
	
	
	public void addBrowser(String browser) {
		browsersToRun.offer(browser);
		localBrowserNameForTestInit = browser;
	}
	
	
	public String currentBrowser() {
		return browsersToRun.peek();
	}
	
	
	public String removeCurrentBrowser() {
		String current = "";
		if (firstBrowser) {
			firstBrowser = false;
		} else {
			current = browsersToRun.poll();
		}
		return current;
	}



	public WebDriver getWebDriver() {
		return webDriver;
	}


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


	public String getLocalBrowserNameForTestInit() {
		return localBrowserNameForTestInit;
	}


	public String getBrowserName(String currentBrowser) {
		
		return browserNameMap.get(currentBrowser);
	}

}
