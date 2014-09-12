package com.mkl.websuites.tests;

import java.util.List;

import junit.framework.Test;

import com.mkl.websuites.MultiBrowserSuite;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;

public abstract class SingleScenarioFileTest extends MultiBrowserSuite {

	

	protected abstract String getScenarioFileLocation();
	
	
	@Override
	protected List<Test> defineTests() {
		
		final String fileLocation = getScenarioFileLocation();
		
		ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);
		
		List<Test> scenarioTests = scenarioFileProcessor.processSingleScenarioFile(fileLocation);
		
		return scenarioTests;
		
	}
}
