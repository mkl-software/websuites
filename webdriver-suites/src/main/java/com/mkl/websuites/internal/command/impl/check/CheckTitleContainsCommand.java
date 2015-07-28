package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkTitleContains", argumentTypes = {String.class})
public class CheckTitleContainsCommand extends CheckTitleCommand {

	public CheckTitleContainsCommand(String expectedTitle) {
		super(expectedTitle);
	}
	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatTitle, String title) {
		
		assertThatTitle
			.overridingErrorMessage("Page title expected to contain '%s', but the title was %s",
					expectedTitle, title)
			.contains(expectedTitle);
	}

	
	
}
