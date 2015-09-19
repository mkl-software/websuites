package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.List;

import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckTextPresentCommand;


@CommandDescriptor(name = "~softCheckText", argumentTypes = String.class)
public class NegSoftCheckTextPresentCommand extends NegCheckTextPresentCommand {

	public NegSoftCheckTextPresentCommand(String text) {
		super(text);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return softly.assertThat((List<WebElement>) args[0]);
	}

}
