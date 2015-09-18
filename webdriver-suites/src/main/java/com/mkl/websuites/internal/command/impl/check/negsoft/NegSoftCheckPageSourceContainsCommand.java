package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckPageSourceContainsCommand;

@CommandDescriptor(name = "~softCheckPageSourceContains", argumentTypes = String.class)
public class NegSoftCheckPageSourceContainsCommand extends
		NegCheckPageSourceContainsCommand {

	public NegSoftCheckPageSourceContainsCommand(String pageSource) {
		super(pageSource);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}

}
