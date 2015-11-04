package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeNameCommand;


@CommandDescriptor(name = "clickAttributeNamed", argumentTypes = {String.class})
public class ClickAttributeNameCommand extends CheckAttributeNameCommand {

	public ClickAttributeNameCommand(String attribute) {
		super(attribute);
	}

	
	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		super.runSingleStringAssertion(assertion, string);
		
		elementWithAttribute.click();
	}
}
