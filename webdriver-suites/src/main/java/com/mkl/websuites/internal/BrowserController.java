package com.mkl.websuites.internal;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.openqa.selenium.WebDriver;

public class BrowserController {

	
	private Queue<String> browsersToRun = new LinkedBlockingQueue<String>();

	private WebDriver webDriver;
	
	private boolean firstBrowser = true;
	
	private String localBrowserNameForTestInit;
	
	
	private static BrowserController instance = new BrowserController();
	
	public static BrowserController getInstance() {
		return instance;
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


	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}


	public String getLocalBrowserNameForTestInit() {
		return localBrowserNameForTestInit;
	}

}
