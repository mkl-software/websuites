package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "~softCheckUrl", argumentTypes = String.class)
public class NegSoftCheckUrlCommand extends NegCheckUrlCommand {

	public NegSoftCheckUrlCommand(String url) {
		super(url);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}

}
