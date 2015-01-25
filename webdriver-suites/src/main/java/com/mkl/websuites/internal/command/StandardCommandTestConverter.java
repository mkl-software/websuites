package com.mkl.websuites.internal.command;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.VisibleForTesting;

import com.mkl.websuites.internal.command.impl.flow.ControlFlowHandler;
import com.mkl.websuites.internal.command.impl.flow.Subtestable;

import junit.framework.Test;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;



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
		// TODO Auto-generated method stub
		return null;
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
