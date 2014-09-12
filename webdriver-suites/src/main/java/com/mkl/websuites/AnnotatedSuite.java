package com.mkl.websuites;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AnnotatedSuite extends MultiBrowserSuite {



	@Override
	protected List<Test> defineTests() {
		
		StandaloneSuite config = this.getClass().getAnnotation(StandaloneSuite.class);
		
		if (config == null) {
			
			log.error("missing SuiteClasses annotation");
			
			throw new WebSuitesException("Missing \"SuiteClasses\" annotation on suite class :" 
			+ getClass().getName() + ". Add the annotation and defined test suites there.");
		}
		
		
		Class<? extends Test>[] suiteClasses = config.suite();
		
		
		List<Test> result = new ArrayList<Test>();
		
		for (Class<? extends Test> suiteClass : suiteClasses) {
			
			try {
				Test dynamicSuite = suiteClass.newInstance();
				
				result.add(dynamicSuite);
				
			} catch (InstantiationException | IllegalAccessException
					|  SecurityException e) {
				
				log.error("error while creating test suite for " + this.getClass().getName() +
						": " + e.getLocalizedMessage());
				
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
