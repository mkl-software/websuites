package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkTitle", argumentTypes = {String.class})
public class CheckTitleCommand extends BaseCommand {

	
	private String expectedTitle;
	
	
	public CheckTitleCommand(String expectedTitle) {
		super();
		this.expectedTitle = expectedTitle;
	}


	@Override
	protected void runStandardCommand() {
		
		String title = browser.getTitle();
		StringAssert checkIfTitle = titleAssertion(title);
		checkIfTitle
			.isEqualTo(expectedTitle)
			.overridingErrorMessage("Expecting web page title to be '%s', but was '%s'", expectedTitle, title);
	}


	protected StringAssert titleAssertion(String title) {
		return assertThat(title);
	}

}
