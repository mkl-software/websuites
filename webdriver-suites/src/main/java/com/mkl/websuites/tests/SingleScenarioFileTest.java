package com.mkl.websuites.tests;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import com.mkl.websuites.MultiBrowserSuite;
import com.mkl.websuites.MultiBrowserTestCase;

public abstract class SingleScenarioFileTest extends MultiBrowserSuite {

	

	protected abstract String getScenarioFileLocation();
	
	
	@Override
	protected List<Test> defineTests() {
		
		final String fileLocation = getScenarioFileLocation();
		
		MultiBrowserTestCase scenarioFileTestCase = new MultiBrowserTestCase() {
			
			
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
