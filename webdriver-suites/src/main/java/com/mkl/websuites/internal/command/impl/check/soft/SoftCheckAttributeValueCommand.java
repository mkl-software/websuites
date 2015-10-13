package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueCommand;


@CommandDescriptor(name = "softCheckAttributeValue", argumentTypes = {String.class})
public class SoftCheckAttributeValueCommand extends CheckAttributeValueCommand {

	public SoftCheckAttributeValueCommand(String attribute) {
		super(attribute);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}