package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckHeaderContainsMatches;


@CommandDescriptor(name = "softCheckHeaderMatches", argumentTypes = {String.class})
public class SoftCheckHeaderContainsMatches extends CheckHeaderContainsMatches {

	public SoftCheckHeaderContainsMatches(String expectedHeader) {
		super(expectedHeader);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}
