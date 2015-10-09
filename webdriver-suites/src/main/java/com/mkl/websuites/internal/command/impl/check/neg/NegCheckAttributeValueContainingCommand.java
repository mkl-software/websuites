package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueContainingCommand;


@CommandDescriptor(name = "~checkAttributeValueContaining", argumentTypes = {String.class})
public class NegCheckAttributeValueContainingCommand extends
		CheckAttributeValueContainingCommand {

	public NegCheckAttributeValueContainingCommand(String attribute) {
		super(attribute);
	}

	
	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {

		String elemDesc = elementWithAttribute != null ? elementWithAttribute.getAttribute("outerHTML") : "";
		
		assertion
			.overridingErrorMessage("The attribute with value containing '%s' is expected NOT to appear in any "
					+ "element on the page but was found in the fragmet '%s'", attribute, elemDesc)
			.isNull();
	}
}
