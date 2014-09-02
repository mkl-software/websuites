package com.mkl.websuites.tests;

import java.util.Arrays;
import java.util.List;

import com.mkl.websuites.MultiBrowserSuite;
import com.mkl.websuites.MultiBrowserTestCase;
import com.mkl.websuites.WebSuitesConfig;

import junit.framework.Test;

public abstract class OneFileScenarioTest extends MultiBrowserSuite {

	
	


	public OneFileScenarioTest(String browserId, WebSuitesConfig config) {
		super(browserId, config);
	}


	protected abstract String getScenarioFileLocation();
	
	
	@Override
	protected List<Test> defineTests() {
		
		final String fileLocation = getScenarioFileLocation();
		
		MultiBrowserTestCase scenarioFileTestCase = new MultiBrowserTestCase(this) {
			
			
			@Override
			protected void runLocally() {
				assertTrue(true);
			}

			@Override
			protected String getTestName() {
				return fileLocation;
			}
		};
		
		return Arrays.asList((Test) scenarioFileTestCase);
	}
}
