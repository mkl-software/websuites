package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckHeaderContainsMatches;
import com.mkl.websuites.internal.command.impl.check.CheckUtils;


@CommandDescriptor(name = "~checkHeaderMatches", argumentTypes = {String.class})
public class NegCheckHeaderContainsMatches extends CheckHeaderContainsMatches {

	public NegCheckHeaderContainsMatches(String expectedHeader) {
		super(expectedHeader);
	}

	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatHeader, String header) {
		
		assertThatHeader
			.overridingErrorMessage("Expecting web page header NOT to match regexp '%s', but was '%s'",
					expectedHeader, header)
			.doesNotMatch(CheckUtils.patternOf(expectedHeader));
	}
}
