package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;

import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;



@Slf4j
@RunWith(InternalWebSuitesRunner.class)
public class WebSuites {

	
	
	private static Class<?> runningClass;
	
	
	public static TestSuite suite() throws
			InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {

		log.debug("suite method");
		
		TestSuite suite = new TestSuite();
		suite.setName("Master multi-browser test");
		
		WebSuitesRunner runner = runningClass.getAnnotation(WebSuitesRunner.class);
		
		Class<? extends MultiBrowserSuite>[] suites = runner.suite();
		
		WebSuitesConfig config = runner.configurationClass().
				getAnnotation(WebSuitesConfig.class);
		
		String[] browsers = config.browsers();
		
		for (String browser : browsers) {
		
			TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");
			
			for (Class<? extends MultiBrowserSuite> testClass : suites) {
				
				MultiBrowserSuite dynamicTest = testClass
						.getConstructor(String.class, WebSuitesConfig.class)
						.newInstance(browser, config);
				
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
