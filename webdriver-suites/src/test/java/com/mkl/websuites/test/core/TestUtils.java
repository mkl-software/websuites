package com.mkl.websuites.test.core;

import static org.junit.Assert.fail;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestUtils {

	
	
	public static void checkIfNoFailures(Result result) {
		if (result.getFailureCount() > 0) {
			
			System.out.println(result.getFailures());
			
			StringBuffer sb = new StringBuffer();
			
			for (Failure failure : result.getFailures()) {
				sb.append("[" + failure.getTestHeader() + "] ")
					.append(failure.getMessage());
					
			}
			
			fail("There are failurs in the unerlying test being invoked, see "
					+ "details below:\n" + sb.toString());
		}
	}
}
