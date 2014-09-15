package com.mkl.websuites.internal.command;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class StandardCommandTestConverter implements CommandTestConverter {



	private static StandardCommandTestConverter instance = new StandardCommandTestConverter();


	public static StandardCommandTestConverter getInstance() {
		return instance ;
	}
	
	
	
	@Override
	public List<Test> convertCommandsToTests(final List<Command> parsedCommands,
			final String scenarioFileName) {
		
		List<Test> tests = new ArrayList<Test>();
		
		Test test = new TestCase() {
			
			@Override
			public String getName() {
				return scenarioFileName;
			}
			
			@Override
			protected void runTest() throws Throwable {
				
				for (Command command : parsedCommands) {
				
					log.debug("running command from " + command);
					command.run();
					
				}
			}
		};
		
		tests.add(test);
		
		return tests;
	}

}
