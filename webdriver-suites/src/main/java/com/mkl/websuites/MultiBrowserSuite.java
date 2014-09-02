package com.mkl.websuites;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class MultiBrowserSuite extends TestSuite {

	
	protected String browserId;
	
	protected WebSuitesConfig configuration;
	
	

	public MultiBrowserSuite(String browserId, WebSuitesConfig config) {
		
		this.browserId = browserId;
		
		this.configuration = config;
		
		for (Test test : defineTests()) {
			
			if (test instanceof MultiBrowserSuite || test instanceof MultiBrowserTestCase) {
				
				addTest(test);
				
			} else {
				
				log.error("trying to add non-supported test case");
				
				throw new WebSuitesException("Trying to add non-supported test case with class "
						+ test.getClass().getName() + ", only MultiBrowserSuite or "
						+ "MultiBrowserTestCase are supported");
				
			}
		}
	}
	
	
	@Override
	public String getName() {
		
		return this.getClass().getName();
	}
	
	
	protected abstract List<Test> defineTests();
	
}
