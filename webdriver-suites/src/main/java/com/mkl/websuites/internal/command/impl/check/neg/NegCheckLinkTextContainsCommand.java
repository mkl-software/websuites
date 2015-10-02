package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextContainsCommand;

@CommandDescriptor(name = "~checkLinkTextContaining", argumentTypes = {String.class})
public class NegCheckLinkTextContainsCommand extends
		CheckLinkTextContainsCommand {

	public NegCheckLinkTextContainsCommand(String expectedLinkText) {
		super(expectedLinkText);
	}
	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		
		assertion
			.overridingErrorMessage("Expecting link containing text '%s'"
					+ " NOT to exist, but found link: '%s'", expectedLinkText, actualLinkText)
			.isNull();
	}

}
