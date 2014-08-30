package com.mkl.websuites;

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public abstract class MultiBrowserSuite extends TestSuite {


	public MultiBrowserSuite() {
		for (TestCase test : defineTests()) {
			addTest(test);
		}
	}
	
	
	protected abstract List<TestCase> defineTests();
	
}
