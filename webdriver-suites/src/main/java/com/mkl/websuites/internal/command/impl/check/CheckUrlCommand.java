package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkUrlContains", argumentTypes = String.class)
public class CheckUrlCommand extends BaseCommand {

	
	protected String expectedUrl;
	
	public CheckUrlCommand(String url) {
		this.expectedUrl = url;
	}

	@Override
	protected void runStandardCommand() {
		String currentUrl = browser.getCurrentUrl();
		StringAssert checkIfTitle = urlAssertion(currentUrl);
		urlAssertionLogic(checkIfTitle);
	}

	protected void urlAssertionLogic(StringAssert checkIfTitle) {
		checkIfTitle.contains(expectedUrl);
	}

	protected StringAssert urlAssertion(String currentUrl) {
		return assertThat(currentUrl);
	}

}
