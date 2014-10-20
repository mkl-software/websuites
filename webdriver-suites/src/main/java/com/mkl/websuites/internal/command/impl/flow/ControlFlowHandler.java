package com.mkl.websuites.internal.command.impl.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.impl.ParameterizedCommand;

@Slf4j
public abstract class ControlFlowHandler extends ParameterizedCommand {

	
	protected List<Command> nestedCommands = new ArrayList<Command>();
	
	public ControlFlowHandler(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	
	public ControlFlowHandler() {
		// for compatibility only, control flow command will only take maps as parameters:
		this (new HashMap<String, String>());
		log.error("Control flow handlers may not use default constructors, "
				+ "please use Map<String, String> constructor instead.");
	}
	
	@Override
	protected void runStandardCommand() {
		runCommandWithParameters();
	}


	public void setNestedCommands(List<Command> nestedCommands) {
		this.nestedCommands = nestedCommands;
	}


	public List<Command> getNestedCommands() {
		return nestedCommands;
	}
	
	


}
