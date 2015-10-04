package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckLinkTextMatchingCommand;

@CommandDescriptor(name = "~softCheckLinkTextMatching", argumentTypes = {String.class})
public class NegSoftCheckLinkTextMatchingCommand extends
		NegCheckLinkTextMatchingCommand {

	public NegSoftCheckLinkTextMatchingCommand(String expectedLinkText) {
		super(expectedLinkText);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
