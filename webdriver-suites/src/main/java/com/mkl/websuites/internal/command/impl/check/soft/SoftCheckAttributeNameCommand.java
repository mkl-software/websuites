package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeNameCommand;


@CommandDescriptor(name = "softCheckAttributeName", argumentTypes = {String.class})
public class SoftCheckAttributeNameCommand extends CheckAttributeNameCommand {

	public SoftCheckAttributeNameCommand(String attribute) {
		super(attribute);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}

}
