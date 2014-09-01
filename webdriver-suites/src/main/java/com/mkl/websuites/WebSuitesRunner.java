package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.Test;
import lombok.extern.slf4j.Slf4j;

import org.junit.internal.runners.JUnit38ClassRunner;


@Slf4j
public class WebSuitesRunner extends JUnit38ClassRunner {

	public WebSuitesRunner(Class<?> klass) throws Throwable {
		super(testFromSuiteMethod(klass));
		log.debug("custom runner initialized");
		
	}

	public static Test testFromSuiteMethod(Class<?> klass) throws Throwable {
		Method suiteMethod= null;
		Test suite= null;
		try {
			suiteMethod= klass.getMethod("suite");
			if (! Modifier.isStatic(suiteMethod.getModifiers())) {
				throw new Exception(klass.getName() + ".suite() must be static");
			}
		
			WebSuites.setRunFor(klass);
			
			suite= (Test) suiteMethod.invoke(null); // static method
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
		return suite;
	}

	
	

	
}
