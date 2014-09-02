package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.CleanupBrowsersTest;
import com.mkl.websuites.internal.SwitchBrowserTest;
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
			
			BrowserController.getInstance().addBrowser(browser);
		
			TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");
			
			browserSuite.addTest(new SwitchBrowserTest(browser));
			
			for (Class<? extends MultiBrowserSuite> testClass : suites) {
				
				MultiBrowserSuite dynamicTest = testClass
						.getConstructor(String.class, WebSuitesConfig.class)
						.newInstance(browser, config);
				
				browserSuite.addTest(dynamicTest);
			}
			
//			BrowserController.getInstance().setFirstTestClass(suites[0]);
//			BrowserController.getInstance().setLastTestClass(suites[suites.length - 1]);
			
			suite.addTest(browserSuite);
		}
		
		suite.addTest(new CleanupBrowsersTest());
		
		return suite;
	}

	public static void setRunFor(Class<?> class1) {
		runningClass = class1;
	}
}
