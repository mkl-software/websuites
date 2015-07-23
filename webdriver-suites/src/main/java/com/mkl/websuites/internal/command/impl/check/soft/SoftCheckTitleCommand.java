package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTitleCommand;


@CommandDescriptor(name = "softCheckTitle", argumentTypes = {String.class})
public class SoftCheckTitleCommand extends CheckTitleCommand {

	public SoftCheckTitleCommand(String expectedTitle) {
		super(expectedTitle);
	}
	
	
	@Override
	protected StringAssert titleAssertion(String title) {
		return softly.assertThat(title);
	}

}
