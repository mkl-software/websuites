package com.mkl.websuites;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public abstract class OneFileScenarioTest extends GenericSuite {

	
	protected abstract String getScenarioFileLocation();
	
	
	@Override
	protected List<TestCase> defineTests() {
		
		final String fileLocation = getScenarioFileLocation();
		
		TestCase scenarioFileTestCase = new TestCase() {
			
			@Override
			public String getName() {
				return fileLocation + " [" + browserId + "]";
			}
			
			@Override
			protected void runTest() throws Throwable {
				System.out.println("running web test for file: " + fileLocation);
				assertTrue(true);
			}
		};
		
		return Arrays.asList(scenarioFileTestCase);
	}
}
