package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkHeaderMatches", argumentTypes = {String.class})
public class CheckHeaderContainsMatches extends CheckHeaderContainsCommand {

	public CheckHeaderContainsMatches(String expectedHeader) {
		super(expectedHeader);
	}

	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatHeader, String header) {
		
		assertThatHeader
			.overridingErrorMessage("Expecting web page header to match regexp '%s', but was '%s'", expectedHeader, header)
			.matches(CheckUtils.patternOf(expectedHeader));
	}
}
