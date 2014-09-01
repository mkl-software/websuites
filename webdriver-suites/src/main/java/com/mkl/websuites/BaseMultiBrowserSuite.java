package com.mkl.websuites;

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public abstract class BaseMultiBrowserSuite extends TestSuite {

	
	protected String browserId;

	public BaseMultiBrowserSuite(String browserId) {
		this.browserId = browserId;
		for (TestCase test : defineTests()) {
			addTest(test);
		}
	}
	
	
	protected abstract List<MultiBrowserTestCase> defineTests();
	
}
