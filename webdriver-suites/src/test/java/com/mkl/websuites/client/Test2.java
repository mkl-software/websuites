package com.mkl.websuites.client;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import com.mkl.websuites.MultiBrowserSuite;
import com.mkl.websuites.MultiBrowserTestCase;

public class Test2 extends MultiBrowserSuite {

	
	public Test2(String browserId) {
		super(browserId);
	}

	@Override
	protected List<Test> defineTests() {
		List<Test> result = new ArrayList<Test>();
		result.add(new MultiBrowserTestCase(browserId) {
			
			
			@Override
			protected void runTest() throws Throwable {
				assertTrue(true);
			}

			@Override
			protected String getTestName() {
				return "user defined test";
			}
		});
		return result;
	}

}
