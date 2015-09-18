package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckPageSourceContainsCommand;


@CommandDescriptor(name = "softCheckPageSourceContains", argumentTypes = String.class)
public class SoftCheckPageSourceContainsCommand extends
		CheckPageSourceContainsCommand {

	public SoftCheckPageSourceContainsCommand(String pageSource) {
		super(pageSource);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
