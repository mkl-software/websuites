package com.mkl.websuites;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
	
	
	public static TestSuite suite() throws
			InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {

		log.debug("suite method");
		
		TestSuite suite = new TestSuite();
		suite.setName("Master multi-browser test");
		
		WebdriverSuites config = runningClass.getAnnotation(WebdriverSuites.class);
		
		Class<? extends MultiBrowserSuite>[] suites = config.suite();
		
		String[] browsers = config.configurationClass().
				getAnnotation(WebdriverSuitesConfiguration.class).browsers();
		
		for (String browser : browsers) {
		
			TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");
			
			for (Class<? extends MultiBrowserSuite> testClass : suites) {
				
				MultiBrowserSuite dynamicTest = testClass
						.getConstructor(String.class)
						.newInstance(browser);
				
				browserSuite.addTest(dynamicTest);
			}
			
			suite.addTest(browserSuite);
		}
		
		return suite;
	}

	public static void setRunFor(Class<?> class1) {
		runningClass = class1;
	}
}
