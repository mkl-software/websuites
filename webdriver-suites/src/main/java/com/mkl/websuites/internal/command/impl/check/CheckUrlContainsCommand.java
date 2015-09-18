package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkUrlContains", argumentTypes = String.class)
public class CheckUrlContainsCommand extends CheckUrlCommand {

	

	public CheckUrlContainsCommand(String url) {
		super(url);
	}

	@Override
	protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {
		
		assertThatUrl
			.overridingErrorMessage("Page URL expected to contain '%s', but the URL was '%s",
					expectedUrl, currentUrl)
			.contains(expectedUrl);
		
	}



}
