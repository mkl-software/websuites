package com.mkl.websuites;

import java.util.Arrays;
import java.util.List;

public abstract class OneFileScenarioTest extends BaseMultiBrowserSuite {

	
	public OneFileScenarioTest(String browserId) {
		super(browserId);
	}


	protected abstract String getScenarioFileLocation();
	
	
	@Override
	protected List<MultiBrowserTestCase> defineTests() {
		
		final String fileLocation = getScenarioFileLocation();
		
		MultiBrowserTestCase scenarioFileTestCase = new MultiBrowserTestCase(browserId) {
			
			
			@Override
			protected void runTest() throws Throwable {
				assertTrue(true);
			}

			@Override
			protected String getTestName() {
				return fileLocation;
			}
		};
		
		return Arrays.asList(scenarioFileTestCase);
	}
}
