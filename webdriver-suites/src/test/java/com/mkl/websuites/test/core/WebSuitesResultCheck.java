package com.mkl.websuites.test.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;

public abstract class WebSuitesResultCheck extends JettyBasedTest {

	
	
	protected void checkWebTestResult() throws Throwable {
		
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(getRunnerClass()));
		
		assertEquals(defineExpectedRunCount(), result.getRunCount());
		
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
		
		additionalChecks();
		
	}

	protected void additionalChecks() {}

	protected abstract int defineExpectedRunCount();

	protected abstract Class<?> getRunnerClass();
	
}
