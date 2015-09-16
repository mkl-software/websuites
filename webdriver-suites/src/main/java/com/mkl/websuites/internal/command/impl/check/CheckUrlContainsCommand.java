package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkUrlContains", argumentTypes = String.class)
public class CheckUrlContainsCommand extends AbstractSingleStringCheck {

	
	protected String expectedUrl;
	
	public CheckUrlContainsCommand(String url) {
		this.expectedUrl = url;
	}

	@Override
	protected String getStringParam() {
		return browser.getCurrentUrl();
	}

	@Override
	protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {
		
		assertThatUrl
			.overridingErrorMessage("Page URL expected to contain '%s', but the URL was %s",
					expectedUrl, currentUrl)
			.contains(expectedUrl);
		
	}



}
