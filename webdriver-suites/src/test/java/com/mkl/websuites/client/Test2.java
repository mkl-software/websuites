package com.mkl.websuites.client;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.mkl.websuites.BaseMultiBrowserSuite;
import com.mkl.websuites.MultiBrowserTestCase;

public class Test2 extends BaseMultiBrowserSuite {

	
	public Test2(String browserId) {
		super(browserId);
	}

	@Override
	protected List<MultiBrowserTestCase> defineTests() {
		return Arrays.asList(new MultiBrowserTestCase[] {new MultiBrowserTestCase(browserId) {
			
			
			@Override
			protected void runTest() throws Throwable {
				assertTrue(true);
			}

			@Override
			protected String getTestName() {
				return "user defined test";
			}
		}});
	}

}
