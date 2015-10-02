package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextContainsCommand;


@CommandDescriptor(name = "softCheckLinkTextContaining", argumentTypes = {String.class})
public class SoftCheckLinkTextContainsCommand extends
		CheckLinkTextContainsCommand {

	public SoftCheckLinkTextContainsCommand(String expectedLinkText) {
		super(expectedLinkText);
	}
	
	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}

}
