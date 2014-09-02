package com.mkl.websuites.internal;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.MultiBrowserSuite;

public class BrowserController {

	
	private Queue<String> browsersToRun = new LinkedBlockingQueue<String>();


	private Class<? extends MultiBrowserSuite> firstSuiteClass;


	private Class<? extends MultiBrowserSuite> lastSuiteClass;


	private WebDriver webDriver;
	
	
	private static BrowserController instance = new BrowserController();
	
	public static BrowserController getInstance() {
		return instance;
	}

	
	public void addBrowser(String browser) {
		browsersToRun.offer(browser);
	}
	
	
	public String checkNextBrowser() {
		return browsersToRun.peek();
	}
	
	
	public String removeCurrentBrowser() {
		return browsersToRun.poll();
	}


	public void setFirstTestClass(Class<? extends MultiBrowserSuite> suiteClass) {
		this.firstSuiteClass = suiteClass;
	}


	public void setLastTestClass(Class<? extends MultiBrowserSuite> suiteClass) {
		this.lastSuiteClass = suiteClass;
		
	}


	public Class<? extends MultiBrowserSuite> getFirstSuiteClass() {
		return firstSuiteClass;
	}


	public Class<? extends MultiBrowserSuite> getLastSuiteClass() {
		return lastSuiteClass;
	}


	public WebDriver getWebDriver() {
		return webDriver;
	}


	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

}
