package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;


@CommandDescriptor(name = "checkHeaderMatches", argumentTypes = {String.class})
public class CheckHeaderMatchesCommand extends CheckHeaderContainsCommand {

	public CheckHeaderMatchesCommand(String expectedHeader) {
		super(expectedHeader);
	}

	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatHeader, String header) {
		
		assertThatHeader
			.overridingErrorMessage("Expecting web page header to match regexp '%s', but was '%s'", expectedHeader, header)
			.matches(CommandUtils.patternOf(expectedHeader));
	}
}
