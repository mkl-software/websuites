package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkAttributeValueMatching", argumentTypes = {String.class})
public class CheckAttributeValueMatchingCommand extends
		CheckAttributeValueCommand {

	public CheckAttributeValueMatchingCommand(String attribute) {
		super(attribute);
	}

	
	@Override
	protected String getPredicateString() {
		return "arguments[0].attributes[i].value != null && "
				+ "arguments[0].attributes[i].value.match('^' + arguments[1] + '$') != null";
	}
	
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		
		assertion
			.overridingErrorMessage("The attribute with value matching '%s' is expected to appear somewhere on the page "
					+ "(in any element)", attribute)
			.isEqualTo("OK");
	}
}
