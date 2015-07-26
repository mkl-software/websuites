package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkUrlContains", argumentTypes = String.class)
public class CheckUrlContainsCommand extends BaseCommand {

	
	protected String expectedUrl;
	
	public CheckUrlContainsCommand(String url) {
		this.expectedUrl = url;
	}

	@Override
	protected void runStandardCommand() {
		String currentUrl = browser.getCurrentUrl();
		StringAssert checkIfUrl = urlAssertion(currentUrl);
		urlAssertionLogic(currentUrl, checkIfUrl);
	}

	protected void urlAssertionLogic(String currentUrl, StringAssert checkIfUrl) {
		checkIfUrl
			.overridingErrorMessage("Page URL expected to contain '%s', but the URL was %s",
					expectedUrl, currentUrl)
			.contains(expectedUrl);
	}

	protected StringAssert urlAssertion(String currentUrl) {
		return assertThat(currentUrl);
	}

}
