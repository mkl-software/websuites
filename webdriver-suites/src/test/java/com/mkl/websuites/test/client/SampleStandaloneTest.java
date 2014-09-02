package com.mkl.websuites.test.client;

import com.mkl.websuites.MultiBrowserSuite;
import com.mkl.websuites.MultiBrowserTestCase;

public class SampleStandaloneTest extends MultiBrowserTestCase {

	public SampleStandaloneTest(MultiBrowserSuite parentSuite) {
		super(parentSuite);
	}

	@Override
	protected void runLocally() {
	}

	@Override
	protected String getTestName() {
		return "user defined test";
	}

	

}
