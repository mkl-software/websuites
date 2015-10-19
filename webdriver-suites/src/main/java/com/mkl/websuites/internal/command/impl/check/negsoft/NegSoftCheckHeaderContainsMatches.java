package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckHeaderContainsMatches;


@CommandDescriptor(name = "~softCheckHeaderMatches", argumentTypes = {String.class})
public class NegSoftCheckHeaderContainsMatches extends
		NegCheckHeaderContainsMatches {

	public NegSoftCheckHeaderContainsMatches(String expectedHeader) {
		super(expectedHeader);
	}

	
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return soft(args);
	}
}