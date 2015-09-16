package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckTitleCommand;


@CommandDescriptor(name = "~softCheckTitle", argumentTypes = String.class)
public class NegSoftCheckTitleCommand extends NegCheckTitleCommand {

	public NegSoftCheckTitleCommand(String expectedTitle) {
		super(expectedTitle);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
	

}