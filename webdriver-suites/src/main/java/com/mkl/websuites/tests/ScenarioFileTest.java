package com.mkl.websuites.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import com.mkl.websuites.internal.MultiBrowserSuite;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;

public class ScenarioFileTest extends MultiBrowserSuite {

	

	// TODO : TEMP for compilation fixing
	public ScenarioFileTest() {
	}
	
	public ScenarioFileTest(String path) {
		super(path);
	}


	@Override
	protected List<Test> defineTests() {
		
		ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);
		
		List<Test> scenarioTests = new ArrayList<Test>();
		
		String path = (String) genericParams[0];
		
		scenarioTests.addAll(scenarioFileProcessor.processSingleScenarioFile(path));
		
		return scenarioTests;
		
	}


}
