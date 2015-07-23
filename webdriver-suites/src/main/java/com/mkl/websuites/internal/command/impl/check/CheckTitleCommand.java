package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkTitle", argumentTypes = {String.class})
public class CheckTitleCommand extends BaseCommand {

	
	protected String expectedTitle;
	
	
	public CheckTitleCommand(String expectedTitle) {
		super();
		this.expectedTitle = expectedTitle;
	}


	@Override
	protected void runStandardCommand() {
		
		String title = browser.getTitle();
		StringAssert checkIfTitle = mainTitleAssertion(title);
		titleAssertionLogic(title, checkIfTitle);
	}


	
	/**
	 * Overriden by different assertions.
	 * @param title
	 * @param checkIfTitle
	 */
	protected void titleAssertionLogic(String title, StringAssert checkIfTitle) {
		checkIfTitle
			.overridingErrorMessage("Expecting web page title to be '%s', but was '%s'", expectedTitle, title)
			.isEqualTo(expectedTitle);
	}



	/**
	 * Overriden by soft assertion.
	 * @param title
	 * @return
	 */
	protected StringAssert mainTitleAssertion(String title) {
		return assertThat(title);
	}

}
