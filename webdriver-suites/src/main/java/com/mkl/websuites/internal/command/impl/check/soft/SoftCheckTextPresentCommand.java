package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.List;

import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTextPresentCommand;

@CommandDescriptor(name = "softCheckText", argumentTypes = {String.class})
public class SoftCheckTextPresentCommand extends CheckTextPresentCommand {

	public SoftCheckTextPresentCommand(String text) {
		super(text);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return softly.assertThat((List<WebElement>) args[0]);
	}

}
