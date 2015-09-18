package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckTitleMatchesCommand;


@CommandDescriptor(name = "~softCheckTitleMatches", argumentTypes = {String.class})
public class NegSoftCheckTitleMatchesCommand extends
		NegCheckTitleMatchesCommand {

	public NegSoftCheckTitleMatchesCommand(String titleFragment) {
		super(titleFragment);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
	
}
