package com.mkl.websuites.internal.scenario;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;

import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandParser;
import com.mkl.websuites.internal.command.CommandProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;



public class ScenarioFileProcessorImpl implements ScenarioFileProcessor {

	
	protected ConfigurationManager config = ServiceFactory.get(ConfigurationManager.class);
	
	
	private static ScenarioFileProcessor instance = new ScenarioFileProcessorImpl();

	public static ScenarioFileProcessor getInstance() {
		return instance ;
	}
	
	
	
	@Override
	public List<Test> processSingleScenarioFile(final String scenarioFileName) {

		File scenarioFile = new File(scenarioFileName);
		
		if (!scenarioFile.exists()) {
			
			return failToLoadScenario(scenarioFileName);
		}
		
		List<Command> parsedCommands = 
				ServiceFactory.get(CommandParser.class).parseCommandFromFile(scenarioFile);
		
		List<Test> convertedCommandsToTests =
				ServiceFactory.get(CommandProcessor.class).convertCommandsToTests(parsedCommands);
		
		return convertedCommandsToTests;
		
	}



	private List<Test> failToLoadScenario(final String scenarioFileName) {
		
		Test failed = new TestCase() {
			@Override
			protected void runTest() throws Throwable {
				fail("Scenario file " + scenarioFileName + " doesn't exist.");
			}
			@Override
			public String getName() {
				return "Scenario file check";
			}
		};
		
		return Arrays.asList(failed);
	}
}
