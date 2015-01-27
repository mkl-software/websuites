package com.mkl.websuites.internal.command;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.VisibleForTesting;

import com.mkl.websuites.internal.command.impl.flow.ControlFlowHandler;
import com.mkl.websuites.internal.command.impl.flow.Subtestable;



@Slf4j
public class StandardCommandTestConverter implements CommandTestConverter {


	
	private List<Command> inputCommandList;
	
	private String masterScenarioFileName;
	
	private boolean hasSubtests;
	

	private static StandardCommandTestConverter instance = new StandardCommandTestConverter();


	public static StandardCommandTestConverter getInstance() {
		return instance ;
	}
	
	
	
	@Override
	public List<Test> convertCommandsToTests(final List<Command> parsedCommands,
			final String scenarioFileName) {
		
		this.inputCommandList = parsedCommands;
		this.masterScenarioFileName = scenarioFileName;
		
		List<Test> convertedTests;
		
		if (!containsSubtests()) {
			
			convertedTests = convertWtihoutSubtests();
		} else {
			
			convertedTests = convertForSubtests();
		}
		
		return convertedTests;
	}



	private List<Test> convertForSubtests() {

		List<Test> resultTestList = new ArrayList<Test>();
		
		TestSuite mainScenarioTopLevelSuit = new TestSuite();
		resultTestList.add(mainScenarioTopLevelSuit);
		
		Test beforeRepeat = new TestCase() {};
		TestSuite repeatSubtests = new TestSuite();
		Test afterRepeat = new TestCase() {};
		
		List<Command> commandsBeforeRepeat = new ArrayList<Command>();
		for (Command command : inputCommandList) {
			if (!(command instanceof Subtestable)) {
				commandsBeforeRepeat.add(command);
			}
		}
		
		mainScenarioTopLevelSuit.addTest(beforeRepeat);
		mainScenarioTopLevelSuit.addTest(repeatSubtests);
		mainScenarioTopLevelSuit.addTest(afterRepeat);
		
		return resultTestList;
	}



	private boolean containsSubtests() {
		
		hasSubtests = false;
		
		checkRecursivelyForSubtests(inputCommandList);
		
		return hasSubtests;
	}



	@VisibleForTesting
	protected void checkRecursivelyForSubtests(List<Command> commands) {
		
		for (Command command : commands) {
			
			if (command instanceof Subtestable) {
				if (((Subtestable) command).isSubtest()) {
					hasSubtests = true;
					break;
				}
			}
			
			if (command instanceof ControlFlowHandler) {
				
				checkRecursivelyForSubtests(((ControlFlowHandler) command).getNestedCommands());
				
			}
		}
	}



	private List<Test> convertWtihoutSubtests() {
		List<Test> tests = new ArrayList<Test>();
		
		Test test = new TestCase() {
			
			@Override
			public String getName() {
				return masterScenarioFileName;
			}
			
			@Override
			protected void runTest() throws Throwable {
				
				for (Command command : inputCommandList) {
				
					log.debug("running command from " + command);
					command.run();
					
				}
			}
		};
		
		tests.add(test);
		
		return tests;
	}

}
