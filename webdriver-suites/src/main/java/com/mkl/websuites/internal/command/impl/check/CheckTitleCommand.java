package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

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
		assertThat(title)
			.isEqualTo(expectedTitle)
			.overridingErrorMessage("Expecting web page title to be '%s', but was '%s'", expectedTitle, title);
	}

}
