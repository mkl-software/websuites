package com.mkl.websuites.internal.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mkl.websuites.internal.services.ServiceFactory;

public class StandardCommandParser implements CommandParser {

	private static final String SCENARIO_DELIMITER = "\t+";
	
	
	private static StandardCommandParser instance = new StandardCommandParser();
	
	public static StandardCommandParser getInstance() {
		return instance;
	}
	
	
	
	@Override
	public List<Command> parseCommandFromFile(
			List<String> preprocessedScenarioFile) {
		
		List<Command> commands = new ArrayList<>();
		
		for (String line : preprocessedScenarioFile) {
			
			String[] tokens = tokenizeLine(line);
			
			Command command = buildCommand(tokens);
			
			commands.add(command);
		}
		
		return commands;
	}

	
	
	
	protected Command buildCommand(String[] tokens) {
		
		CommandBuilder commandManager = ServiceFactory.get(CommandBuilder.class);
		
		String commandName = tokens[0];
		
		String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
		
		Command command = commandManager.instantiateCommand(commandName, arguments);
		
		return command;
	}



	protected String[] tokenizeLine(String line) {
		
		return line.split(SCENARIO_DELIMITER);
	}

}
