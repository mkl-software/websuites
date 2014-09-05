package com.mkl.websuites.internal.runner;

import lombok.extern.slf4j.Slf4j;

import org.junit.internal.runners.JUnit38ClassRunner;

import com.mkl.websuites.WebSuites;


@Slf4j
public class InternalWebSuitesRunner extends JUnit38ClassRunner {

	
	public InternalWebSuitesRunner(Class<?> klass) throws Throwable {
		
		super(new WebSuites().defineMasterSuite(klass));
		
		log.debug("custom runner initialized for runner: " + klass);
		
	}


}
