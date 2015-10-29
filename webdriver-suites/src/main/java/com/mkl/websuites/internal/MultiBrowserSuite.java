package com.mkl.websuites.internal;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;


public abstract class MultiBrowserSuite extends TestSuite {

	protected Object[] genericParams;

	public MultiBrowserSuite(Object ... params) {
		
		genericParams = params;
		
		for (Test test : defineTests()) {
			
			// TODO: might be some validation here
			
//			if (test instanceof MultiBrowserSuite || test instanceof MultiBrowserTestCase) {
//				
//				addTest(test);
//				
//			} else {
//				
//				log.error("trying to add non-supported test case");
//				
//				throw new WebSuitesException("Trying to add non-supported test case with class "
//						+ test.getClass().getName() + ", only MultiBrowserSuite or "
//						+ "MultiBrowserTestCase are supported");
//				
//			}
			
			addTest(test);
		}
	}
	
	
	@Override
	public String getName() {
		
		return this.getClass().getName();
	}
	
	
	protected abstract List<Test> defineTests();
	
}
