package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueMatchingCommand;


@CommandDescriptor(name = "softCheckAttributeValueMatching", argumentTypes = {String.class})
public class SoftCheckAttributeValueMatchingCommand extends
		CheckAttributeValueMatchingCommand {

	public SoftCheckAttributeValueMatchingCommand(String attribute) {
		super(attribute);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
