package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckTitleContainsCommand;


@CommandDescriptor(name = "~softCheckTitleContains", argumentTypes = String.class)
public class NegSoftCheckTitleContainsCommand extends NegCheckTitleContainsCommand {

	public NegSoftCheckTitleContainsCommand(String titleFragment) {
		super(titleFragment);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
