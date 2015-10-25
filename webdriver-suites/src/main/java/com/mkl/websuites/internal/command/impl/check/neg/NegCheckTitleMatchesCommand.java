package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTitleMatchesCommand;
import com.mkl.websuites.internal.command.impl.check.CheckUtils;

@CommandDescriptor(name = "~checkTitleMatches", argumentTypes = {String.class})
public class NegCheckTitleMatchesCommand extends CheckTitleMatchesCommand {

	public NegCheckTitleMatchesCommand(String titleFragment) {
		super(titleFragment);
	}
	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatTitle, String title) {
		
		assertThatTitle
			.overridingErrorMessage("Page title expected NOT to match regex '%s', but the title was '%s'",
					expectedTitle, title)
			.doesNotMatch(CheckUtils.patternOf(expectedTitle));
	}

}
