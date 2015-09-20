package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckTextMatchingCommand;


@CommandDescriptor(name = "~softCheckTextMatching", argumentTypes = String.class)
public class NegSoftCheckTextMatchingCommand extends NegCheckTextMatchingCommand {

	public NegSoftCheckTextMatchingCommand(String regex) {
		super(regex);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return softly.assertThat((Boolean) args[0]);
	}

}
