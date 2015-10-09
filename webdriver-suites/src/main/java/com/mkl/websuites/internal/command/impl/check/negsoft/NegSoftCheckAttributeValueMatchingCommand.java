package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckAttributeValueMatchingCommand;


@CommandDescriptor(name = "~softCheckAttributeValueMatching", argumentTypes = {String.class})
public class NegSoftCheckAttributeValueMatchingCommand extends
		NegCheckAttributeValueMatchingCommand {

	public NegSoftCheckAttributeValueMatchingCommand(String attribute) {
		super(attribute);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
