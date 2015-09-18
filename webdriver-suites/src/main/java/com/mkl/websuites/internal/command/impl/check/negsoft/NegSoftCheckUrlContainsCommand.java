package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckUrlContainsCommand;



@CommandDescriptor(name = "~softCheckUrlContains", argumentTypes = String.class)
public class NegSoftCheckUrlContainsCommand extends NegCheckUrlContainsCommand {

	public NegSoftCheckUrlContainsCommand(String url) {
		super(url);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}

}
