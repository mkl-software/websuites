package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementAttributeValueCommand;


@CommandDescriptor(name = "~checkElementAttrValue", argumentTypes = {String.class, String.class, String.class})
public class NegCheckElementAttributeValueCommand extends
		CheckElementAttributeValueCommand {

	public NegCheckElementAttributeValueCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckElementAttributeValueCommand(String selector,
			String attributeName, String expectedAttributeValue) {
		super(selector, attributeName, expectedAttributeValue);
	}
	
	
	protected class NegCheckElementAttrValue extends CheckElementAttrValue {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting attribute '%s' in the web page element with selector '%s'"
						+ " NOT to have value '%s'", inputAttributeName, by, expectedAttributeValue)
				.isNotEqualTo(expectedAttributeValue);
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementAttrValue();
	}


}
