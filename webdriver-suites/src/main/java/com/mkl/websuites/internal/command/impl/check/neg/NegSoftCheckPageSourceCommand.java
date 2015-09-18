package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "~softCheckPageSource", argumentTypes = String.class)
public class NegSoftCheckPageSourceCommand extends NegCheckPageSourceCommand {

	public NegSoftCheckPageSourceCommand(String pageSource) {
		super(pageSource);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}

}
