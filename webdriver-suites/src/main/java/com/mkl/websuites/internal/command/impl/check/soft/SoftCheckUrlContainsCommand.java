package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckUrlContainsCommand;


@CommandDescriptor(name = "softCheckUrlContains", argumentTypes = String.class)
public class SoftCheckUrlContainsCommand extends CheckUrlContainsCommand{

	public SoftCheckUrlContainsCommand(String expectedUrl) {
		super(expectedUrl);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
	

}
