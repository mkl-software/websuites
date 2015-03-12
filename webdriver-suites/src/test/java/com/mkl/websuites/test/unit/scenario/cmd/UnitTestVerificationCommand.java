package com.mkl.websuites.test.unit.scenario.cmd;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.test.unit.scenario.CommandInvocationVerifier;


@CommandDescriptor(name = "internal-test", argumentTypes = String.class)
public class UnitTestVerificationCommand extends BaseCommand {

	private String message;
	
	public UnitTestVerificationCommand(String message) {
		super();
		this.message = message;
	}

	@Override
	protected void runStandardCommand() {
		
		CommandInvocationVerifier.getInstance().verifyInvocation(populateStringWithProperties(message));

	}

}
