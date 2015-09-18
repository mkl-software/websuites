package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckUrlContainsCommand;


@CommandDescriptor(name = "~checkUrlContains", argumentTypes = String.class)
public class NegCheckUrlContainsCommand extends CheckUrlContainsCommand {

	public NegCheckUrlContainsCommand(String url) {
		super(url);
	}
	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {
		
		assertThatUrl
			.overridingErrorMessage("Page URL expected NOT to contain '%s', but the URL was '%s",
					expectedUrl, currentUrl)
			.doesNotContain(expectedUrl);
		
	}

}
