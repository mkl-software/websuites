package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueContainingCommand;


@CommandDescriptor(name = "clickAttributeValueContaining", argumentTypes = {String.class})
public class ClickAttributeValueContainingCommand extends
		CheckAttributeValueContainingCommand {

	public ClickAttributeValueContainingCommand(String attribute) {
		super(attribute);
	}

	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		super.runSingleStringAssertion(assertion, string);
		
		elementWithAttribute.click();
	}
}
