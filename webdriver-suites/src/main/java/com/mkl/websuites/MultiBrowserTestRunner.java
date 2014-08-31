package com.mkl.websuites;

import junit.framework.TestSuite;



public class MultiBrowserTestRunner extends TestSuite {

	
	public static TestSuite suite() {

		// TODO: read from suiteList file and instantiate suites: 
		
		TestSuite suite = new TestSuite();
		suite.setName("Master multi-browser test");
		suite.addTest(new SampleCategoryTest());
		suite.addTest(new SampleCategoryTest());
		return suite;
	}
}
