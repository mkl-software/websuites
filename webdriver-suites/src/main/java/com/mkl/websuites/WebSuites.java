package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;

import junit.framework.Test;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.CleanupBrowsersTest;
import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.SwitchBrowserTest;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.internal.services.ServiceFactory;



@Slf4j
@RunWith(InternalWebSuitesRunner.class)
public class WebSuites {

	
	public WebSuites() {
		
		ServiceFactory.init(this.getClass());
	}
	
	

	public TestSuite defineMasterSuite(Class<?> runningClass) throws
			InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {

		log.debug("suite method");
		
		TestSuite suite = new TestSuite();
		suite.setName("Master multi-browser test");
		
		WebSuitesRunner runner = runningClass.getAnnotation(WebSuitesRunner.class);
		
		Class<? extends Test>[] suites = runner.suite();
		
		WebSuitesConfig config = runner.configurationClass().
				getAnnotation(WebSuitesConfig.class);
		
		ConfigurationManager.getInstance().setConfiguration(config);
		
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
