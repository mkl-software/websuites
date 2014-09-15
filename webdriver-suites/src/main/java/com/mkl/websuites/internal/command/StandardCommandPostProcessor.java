package com.mkl.websuites.internal.command;

import java.util.List;

public class StandardCommandPostProcessor implements CommandPostProcessor {

	
	private static StandardCommandPostProcessor instance = new StandardCommandPostProcessor();


	public static StandardCommandPostProcessor getInstance() {
		return instance ;
	}
	
	
	
	
	@Override
	public List<Command> postProcessCommands(List<Command> parsedCommands) {
		return parsedCommands;
	}

}
