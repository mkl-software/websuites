package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementAttributeMatchesCommand;


@CommandDescriptor(name = "~checkElementAttrValueMatches", argumentTypes = {String.class, String.class, String.class})
public class NegCheckElementAttributeMatchesCommand extends
		CheckElementAttributeMatchesCommand {

	public NegCheckElementAttributeMatchesCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckElementAttributeMatchesCommand(String selector,
			String attributeName, String expectedAttributeValue) {
		super(selector, attributeName, expectedAttributeValue);
	}

	
	protected class NegCheckElementAttrValueMatches extends CheckElementAttrValue {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting value of attribute '%s' in the web page element with selector '%s'"
						+ " NOT to match regexp '%s', but found '%s'", inputAttributeName, by, expectedAttributeValue,
						foundElement.getAttribute(inputAttributeName))
				.doesNotMatch(expectedAttributeValue);
		}
	}
	
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementAttrValueMatches();
	}
}
