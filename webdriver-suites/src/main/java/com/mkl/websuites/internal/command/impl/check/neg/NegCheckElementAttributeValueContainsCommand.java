package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementAttributeValueContainsCommand;


@CommandDescriptor(name = "~checkElementAttrValueContains", argumentTypes = {String.class, String.class, String.class})
public class NegCheckElementAttributeValueContainsCommand extends
		CheckElementAttributeValueContainsCommand {

	public NegCheckElementAttributeValueContainsCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckElementAttributeValueContainsCommand(String selector,
			String attributeName, String expectedAttributeValue) {
		super(selector, attributeName, expectedAttributeValue);
	}
	
	
	protected class NegCheckElementAttrValueContains extends CheckElementAttrValueContains {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting value of attribute '%s' in the web page element with selector '%s'"
						+ " NOT to contain '%s'", inputAttributeName, by, expectedAttributeValue)
				.doesNotContain(expectedAttributeValue);
		}
	}
	
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementAttrValueContains();
	}

}
