package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextMatchingCommand;


@CommandDescriptor(name = "softCheckLinkTextMatching", argumentTypes = {String.class})
public class SoftCheckLinkTextMatchingCommand extends
		CheckLinkTextMatchingCommand {

	public SoftCheckLinkTextMatchingCommand(String expectedLinkText) {
		super(expectedLinkText);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
