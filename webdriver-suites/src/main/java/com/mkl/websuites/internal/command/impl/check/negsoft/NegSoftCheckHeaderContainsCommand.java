package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckHeaderContainsCommand;


@CommandDescriptor(name = "~softCheckHeaderContains", argumentTypes = {String.class})
public class NegSoftCheckHeaderContainsCommand extends
		NegCheckHeaderContainsCommand {

	public NegSoftCheckHeaderContainsCommand(String expectedHeader) {
		super(expectedHeader);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
