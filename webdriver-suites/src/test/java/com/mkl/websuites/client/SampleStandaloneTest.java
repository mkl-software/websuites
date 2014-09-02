package com.mkl.websuites.client;

import com.mkl.websuites.MultiBrowserSuite;
import com.mkl.websuites.MultiBrowserTestCase;

public class SampleStandaloneTest extends MultiBrowserTestCase {

	public SampleStandaloneTest(MultiBrowserSuite parentSuite) {
		super(parentSuite);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void runLocally() {
		assertTrue(true);
	}

	@Override
	protected String getTestName() {
		return "user defined test";
	}

	

}
