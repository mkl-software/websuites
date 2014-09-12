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
		
		
		for (String browser : browsers) {
			
			browserController.addBrowser(browser);
		
			TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");
			
			browserSuite.addTest(new SwitchBrowserTest(browser));
			
			for (Class<? extends Test> testClass : suites) {
				
				Test dynamicTest = testClass.newInstance();
				
				browserSuite.addTest(dynamicTest);
			}
			
			suite.addTest(browserSuite);
		}
		
		
		suite.addTest(new CleanupBrowsersTest());
		
		
		return suite;
	}
}
