package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTitleCommand;


@CommandDescriptor(name = "~checkTitle", argumentTypes = String.class)
public class NegCheckTitleCommand extends CheckTitleCommand {

	public NegCheckTitleCommand(String expectedTitle) {
		super(expectedTitle);
	}
	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertThatTitle,
			String title) {
		
		assertThatTitle
			.overridingErrorMessage("Expecting web page title NOT to be '%s'", expectedTitle)
			.isNotEqualTo(expectedTitle);
	}

}
