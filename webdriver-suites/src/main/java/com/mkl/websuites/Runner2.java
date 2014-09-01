package com.mkl.websuites;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;

import com.mkl.websuites.client.SampleCategoryTest;


@RunWith(WebSuitesRunner.class)
public class Runner2 {

	public static TestSuite suite() {

		
		TestSuite suite = new TestSuite();
		suite.setName("Master multi-browser test");
		suite.addTest(new SampleCategoryTest());
		suite.addTest(new SampleCategoryTest());
		return suite;
	}
	
}
