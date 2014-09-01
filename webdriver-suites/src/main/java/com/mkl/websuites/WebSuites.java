package com.mkl.websuites;

import org.junit.runner.RunWith;

import junit.framework.Test;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.annotation.WebdriverSuites;
import com.mkl.websuites.internal.annotation.WebdriverSuitesConfiguration;



@Slf4j
@RunWith(WebSuitesRunner.class)
public class WebSuites {

	
	
	private static Class<?> runningClass;
	
	
	public static TestSuite suite() throws InstantiationException, IllegalAccessException {

		log.debug("suite method");
		
		// TODO: read from suiteList file and instantiate suites: 
		
		TestSuite suite = new TestSuite();
		suite.setName("Master multi-browser test");
		WebdriverSuites config = runningClass.getAnnotation(WebdriverSuites.class);
		
		Class<? extends GenericSuite>[] suites = config.suite();
		String[] browsers = config.configurationClass().
				getAnnotation(WebdriverSuitesConfiguration.class).browsers();
		
		for (String browser : browsers) {
		
			TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");
			for (Class<? extends GenericSuite> testClass : suites) {
				
				GenericSuite dynamicTest = testClass.newInstance();
				dynamicTest.browserId = browser;
				
				browserSuite.addTest(dynamicTest);
			}
			
			suite.addTest(browserSuite);
		}
//		suite.addTest(new SampleCategoryTest());
//		suite.addTest(new SampleCategoryTest());
		return suite;
	}

	public static void setRunFor(Class<?> class1) {
		runningClass = class1;
	}
}
