package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;

import junit.framework.Test;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;

import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.browser.CleanupBrowsersTest;
import com.mkl.websuites.internal.browser.SwitchBrowserTest;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.internal.services.ServiceFactory;



@Slf4j
@RunWith(InternalWebSuitesRunner.class)
public class WebSuites {

	
	private Class<?> runningClass;
	
	private static String currentlyDefiningBrowser;
	
	public WebSuites() {}
	
	public WebSuites(Class<?> klass) {
		
		ServiceFactory.init(klass);
		runningClass = klass;
	}
	
	

	public TestSuite defineMasterSuite() throws
			InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {

		log.debug("suite method");
		
		TestSuite suite = new TestSuite();
		suite.setName("Multi-browser test suite");
		
		WebSuitesRunner runner = runningClass.getAnnotation(WebSuitesRunner.class);
		
		Class<? extends Test>[] suites = runner.suite();
		
		WebSuitesConfig config = runner.configurationClass().
				getAnnotation(WebSuitesConfig.class);
		
		ServiceFactory.get(ConfigurationManager.class).setConfiguration(config);
		
		BrowserController browserController = ServiceFactory.get(BrowserController.class);
		
		browserController.initializeBrowsersEnvironment(config);
		
		String[] browsers = config.browsers();
		
		// TODO: refactor it more elegantly
		if (browsers.length == 1 && browsers[0].equals("none")) {
			// run for non-browser mode:
			currentlyDefiningBrowser = "none";
			TestSuite browserSuite = new TestSuite("Running without any browser");
			for (Class<? extends Test> testClass : suites) {
				
				Test dynamicTest = testClass.newInstance();
				browserSuite.addTest(dynamicTest);
			}
			suite.addTest(browserSuite);
		}
		
		for (String browser : browsers) {
			
			if (browser.equals("none")) {
				continue;
			}
			
			currentlyDefiningBrowser = browser;
			
			browserController.addBrowser(browser);
		
			TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");
			
			browserSuite.addTest(new SwitchBrowserTest(browser));
			
			for (Class<? extends Test> testClass : suites) {
				
				Test dynamicTest = testClass.newInstance();
				
				browserSuite.addTest(dynamicTest);
			}
			
			suite.addTest(browserSuite);
		}
		
		if (!config.doNotCloseBrowserAtTheEnd() && !(browsers.length == 1 && browsers[0].equals("none"))) {
			
			suite.addTest(new CleanupBrowsersTest());
		}
		
		
		return suite;
	}

	
	
	/**
	 * To allow quickly identify in the code for which browser
	 * are tests currently defined for.
	 * @return
	 */
	public static String getCurrentlyDefiningBrowser() {
		return currentlyDefiningBrowser;
	}
}
