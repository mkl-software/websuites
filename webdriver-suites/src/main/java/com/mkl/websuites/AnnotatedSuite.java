package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AnnotatedSuite extends MultiBrowserSuite {


	public AnnotatedSuite(String browserId, WebSuitesConfig config) {
		super(browserId, config);
	}

	@Override
	protected List<Test> defineTests() {
		
		SuiteClasses config = this.getClass().getAnnotation(SuiteClasses.class);
		
		if (config == null) {
			
			log.error("missing SuiteClasses annotation");
			
			throw new WebSuitesException("Missing \"SuiteClasses\" annotation on suite class :" 
			+ getClass().getName() + ". Add the annotation and defined test suites there.");
		}
		
		
		Class<? extends MultiBrowserSuite>[] suiteClasses = config.suites();
		
		Class<? extends MultiBrowserTestCase>[] testClasses = config.tests();
		
		List<Test> result = new ArrayList<Test>();
		
		for (Class<? extends MultiBrowserSuite> suiteClass : suiteClasses) {
			
			try {
				MultiBrowserSuite dynamicSuite = suiteClass
						.getConstructor(String.class, WebSuitesConfig.class)
						.newInstance(suiteClass, config);
				
				result.add(dynamicSuite);
				
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				
				log.error("error while creating test suite for " + this.getClass().getName() +
						": " + e.getLocalizedMessage());
				
				e.printStackTrace();
			}
		}
		
		
		for (Class<?extends MultiBrowserTestCase> testClass : testClasses) {
			
			try {
				MultiBrowserTestCase dynamicTest = testClass
						.getConstructor(MultiBrowserSuite.class)
						.newInstance(this);
				
				result.add(dynamicTest);
				
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				
				log.error("error while creating test class for " + this.getClass().getName() +
						": " + e.getLocalizedMessage());
				
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
