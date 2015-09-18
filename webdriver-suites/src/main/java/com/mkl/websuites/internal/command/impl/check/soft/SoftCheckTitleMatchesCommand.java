package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTitleMatchesCommand;


@CommandDescriptor(name = "softCheckTitleMatches", argumentTypes = {String.class})
public class SoftCheckTitleMatchesCommand extends CheckTitleMatchesCommand {

	public SoftCheckTitleMatchesCommand(String titleFragment) {
		super(titleFragment);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}

}
