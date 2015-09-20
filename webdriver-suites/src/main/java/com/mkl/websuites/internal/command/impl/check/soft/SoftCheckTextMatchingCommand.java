package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTextMatchingCommand;


@CommandDescriptor(name = "softCheckTextMatching", argumentTypes = String.class)
public class SoftCheckTextMatchingCommand extends CheckTextMatchingCommand {

	public SoftCheckTextMatchingCommand(String regex) {
		super(regex);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return softly.assertThat((Boolean) args[0]);
	}
}
