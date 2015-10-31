package com.mkl.websuites.itests.cmd;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;




@Slf4j
@CommandDescriptor(
	name = "sample",
	argumentTypes = {String.class}
)
public class SampleCommand implements Command {

	
	private String arg;


	public SampleCommand(String argument) {
		this.arg = argument;
		log.debug("constructor for command invoked with argument: " + argument);
	}
	
	
	@Override
	public void run() {
		
		log.debug("running command with argument: " + arg);

	}

}
