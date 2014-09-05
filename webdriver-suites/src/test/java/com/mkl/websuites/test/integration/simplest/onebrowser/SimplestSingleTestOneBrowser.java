package com.mkl.websuites.test.integration.simplest.onebrowser;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.test.core.JettyBasedTest;

import static org.junit.Assert.*;


public class SimplestSingleTestOneBrowser extends JettyBasedTest {

	

	@Test
	public void test() throws Throwable {
		
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(LocalTestConfig.Runner.class));
		
		assertEquals(calculateExpectedRunCount(1, 1) + 1, result.getRunCount()); // +1 for no-web-suite test
		
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