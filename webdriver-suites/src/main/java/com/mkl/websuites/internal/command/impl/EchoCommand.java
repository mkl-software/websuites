package com.mkl.websuites.internal.command.impl;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;

@CommandDescriptor(name = "echo", argumentTypes = String.class)
public class EchoCommand extends BaseCommand {

	private String message;
	
	public EchoCommand(String message) {
		super();
		this.message = message;
	}

	@Override
	protected void runStandardCommand() {

		System.out.println(populateStringWithProperties(message));
	}

}
