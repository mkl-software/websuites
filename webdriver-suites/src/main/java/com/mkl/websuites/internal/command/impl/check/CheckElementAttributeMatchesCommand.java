package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;


@CommandDescriptor(name = "checkElementAttrValueMatches", argumentTypes = {String.class, String.class, String.class})
public class CheckElementAttributeMatchesCommand extends
		CheckElementAttributeValueCommand {

	public CheckElementAttributeMatchesCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckElementAttributeMatchesCommand(String selector,
			String attributeName, String expectedAttributeValue) {
		super(selector, attributeName, expectedAttributeValue);
	}

	
	protected class CheckElementAttrValueMatches extends CheckElementAttrValue {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting value of attribute '%s' in the web page element with selector '%s'"
						+ " to match regexp '%s', but it is '%s'", inputAttributeName, by, expectedAttributeValue,
						foundElement.getAttribute(inputAttributeName))
				.matches(CommandUtils.patternOf(expectedAttributeValue));
		}
	}
	
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementAttrValueMatches();
	}

}
