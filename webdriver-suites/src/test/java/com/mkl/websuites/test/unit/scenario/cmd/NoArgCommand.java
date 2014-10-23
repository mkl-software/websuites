package com.mkl.websuites.test.unit.scenario.cmd;

import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;



@CommandDescriptor(name = "noArg", argumentTypes = {})
public class NoArgCommand implements Command {

	
	@Override
	public void run() {

	}

}
