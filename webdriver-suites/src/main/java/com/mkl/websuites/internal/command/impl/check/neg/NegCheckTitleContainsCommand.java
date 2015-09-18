package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTitleContainsCommand;


@CommandDescriptor(name = "~checkTitleContains", argumentTypes = String.class)
public class NegCheckTitleContainsCommand extends CheckTitleContainsCommand {

	public NegCheckTitleContainsCommand(String titleFragment) {
		super(titleFragment);
	}

	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatTitle, String title) {
		
		assertThatTitle
			.overridingErrorMessage("Page title expected NOT to contain '%s', but the title was '%s'",
					expectedTitle, title)
			.doesNotContain(expectedTitle);
	}
	
}
