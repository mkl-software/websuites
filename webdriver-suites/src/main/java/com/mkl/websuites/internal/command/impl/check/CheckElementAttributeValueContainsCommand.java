package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkElementAttrValueContains", argumentTypes = {String.class, String.class, String.class})
public class CheckElementAttributeValueContainsCommand extends
		CheckElementAttributeValueCommand {

	public CheckElementAttributeValueContainsCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckElementAttributeValueContainsCommand(String selector,
			String attributeName, String expectedAttributeValue) {
		super(selector, attributeName, expectedAttributeValue);
	}

	
	protected class CheckElementAttrValueContains extends CheckElementAttrValue {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting value of attribute '%s' in the web page element with selector '%s'"
						+ " to contain '%s', but it is '%s'", inputAttributeName, by, expectedAttributeValue,
						foundElement.getAttribute(inputAttributeName))
				.contains(expectedAttributeValue);
		}
	}
	
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementAttrValueContains();
	}
}
