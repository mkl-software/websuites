package com.mkl.websuites.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;

import com.mkl.websuites.internal.MultiBrowserSuite;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;

public abstract class ScenarioFileTest extends MultiBrowserSuite {

	

	@Override
	protected List<Test> defineTests() {
		
		List<String> fileLocations = getScenarioFileLocations();
		
		ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);
		
		List<Test> scenarioTests = new ArrayList<Test>();
		
		for (String fileLocation : fileLocations) {
		
			scenarioTests.addAll(scenarioFileProcessor.processSingleScenarioFile(fileLocation));
		
		}
		return scenarioTests;
		
	}


	protected List<String> getScenarioFileLocations() {
		
		ScenarioFiles config = this.getClass().getAnnotation(ScenarioFiles.class);
		
		String[] paths = config.value();
		
		return Arrays.asList(paths);
	}
}
