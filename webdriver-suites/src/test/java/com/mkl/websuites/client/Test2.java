package com.mkl.websuites.client;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.mkl.websuites.GenericSuite;

public class Test2 extends GenericSuite {

	@Override
	protected List<TestCase> defineTests() {
		return Arrays.asList(new TestCase[] {new TestCase() {
			@Override
			public String getName() {
				return "user defined test [" + browserId + "]";
			}
			
			@Override
			protected void runTest() throws Throwable {
				assertTrue(true);
			}
		}});
	}

}
