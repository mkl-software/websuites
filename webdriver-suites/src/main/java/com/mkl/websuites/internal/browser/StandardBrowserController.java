package com.mkl.websuites.internal.browser;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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
public class StandardBrowserController implements BrowserController {

	
	
	protected Queue<String> browsersToRun = new LinkedBlockingQueue<String>();

	protected WebDriver webDriver;
	
	
	private int globalTimeout;
	
	private boolean firstBrowser = true;
	
	private String localBrowserNameForTestInit;

	private Map<String, Class<?>> driverClassMap = new HashMap<String, Class<?>>();

	private Map<String, String> browserDisplayNameMap = new HashMap<String, String>();
	
	
	
	protected StandardBrowserController() {}
	
	private static BrowserController instance = new StandardBrowserController();
	
	public static BrowserController getInstance() {
		return instance;
	}

	
	
	
	
	@Override
	public void initializeBrowsersEnvironment(WebSuitesConfig config) {
		
		BrowsersConfiguration browsersConfiguration =
				config.browsersConfiguration().getAnnotation(BrowsersConfiguration.class);
		
		if (browsersConfiguration == null) {
			
			log.error("empty browser configuration");
			throw new WebSuitesException("Empty browser configuration, please fill properly "
					+ "the BrowserConfiguration section in the configuration");
		}
		
		globalTimeout = config.waitTimeout();
		
		Browser[] browsers = browsersConfiguration.browsers();
		
		// populate default FF browser in case it's not existing int the browser config annotation:
		driverClassMap.put("ff", FirefoxDriver.class);
		browserDisplayNameMap.put("ff", "Firefox");
		// if it's existing it will overwritten in next loop
		
		for (Browser browser : browsers) {
			
			browserDisplayNameMap.put(browser.localId(), browser.displayName());
			
			switch (browser.browserType()) {
			case CHROME:
				configureBrowser("webdriver.chrome.driver", browser, ChromeDriver.class);
				break;
			case FIREFOX:
				configureBrowser("", browser, FirefoxDriver.class);
				break;
			case INTERNET_EXPLORER:
				configureBrowser("webdriver.ie.driver", browser, InternetExplorerDriver.class);
				break;
			
			}
			
		}
		
	}





	protected void configureBrowser(String envKey, Browser browser, Class<?> driverClass) {
		
		if (!envKey.isEmpty()) {
			System.setProperty(envKey, browser.webDriverPath());
		}
		driverClassMap.put(browser.localId(), driverClass);
	}
	
	
	
	
	
	@Override
	public void addBrowser(String browser) {
		browsersToRun.offer(browser);
		localBrowserNameForTestInit = browser;
	}
	
	
	@Override
	public String currentBrowser() {
		return browsersToRun.peek();
	}
	
	
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



	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}


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
			webDriver.manage().timeouts().implicitlyWait(globalTimeout, TimeUnit.SECONDS);
			
		} catch (InstantiationException | IllegalAccessException e) {
			
			String msg = "cannot create an instance of Web Driver for [" + currentBrowser +
					"] with class: " + driverClass;
			log.error(msg);
			throw new WebSuitesException(msg, e);
		}
	}


	@Override
	public String getLocalBrowserNameForTestInit() {
		return localBrowserNameForTestInit;
	}


	@Override
	public String getBrowserDisplayName(String currentBrowser) {
		
		return browserDisplayNameMap.get(currentBrowser);
	}

}
