package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkLinkHrefMatching", argumentTypes = String.class)
public class CheckLinkHrefMatchingCommand extends CheckLinkHrefCommand {

	public CheckLinkHrefMatchingCommand(String expectedLinkText) {
		super(expectedLinkText);
	}

	
	@Override
	protected boolean predicate(String href) {
		return href == null ? false : href.matches(expectedLinkText);
	}
	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		
		assertion
			.overridingErrorMessage("Expecting link with HREF attribute matching regexp '%s'"
					+ " to exist", expectedLinkText)
			.isEqualTo("OK");
	}
}
