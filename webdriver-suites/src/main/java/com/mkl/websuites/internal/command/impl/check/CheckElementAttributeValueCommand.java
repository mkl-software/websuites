package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkElementAttrValue", argumentTypes = {String.class, String.class, String.class})
public class CheckElementAttributeValueCommand extends
		CheckElementAttributeCommand {

	protected String expectedAttributeValue;
	protected static final String ATT_VALUE_PARAM = "attValue";
	
	public CheckElementAttributeValueCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	@SuppressWarnings("serial")
	public CheckElementAttributeValueCommand(final String selector,
			final String attributeName, final String expectedAttributeValue) {
		
		super(new HashMap<String, String>() {{
			put("css", selector);
			put(ATT_NAME_PARAM, attributeName);
			put(ATT_VALUE_PARAM, expectedAttributeValue);
		}});
	}
	
	
	
	protected class CheckElementAttrValue extends AbstractSingleStringCheck {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String attributeValue) {
			
			assertion
				.overridingErrorMessage("Expecting attribute '%s' in the web page element with selector '%s'"
						+ " to have an exact value '%s' but was '%s'",
						inputAttributeName, by, expectedAttributeValue, attributeValue)
				.isEqualTo(expectedAttributeValue);
		}
		
		@Override
		protected String getStringParam() {
			return actualAttributeValue;
		}
	}
	
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementAttrValue();
	}
	
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		this.expectedAttributeValue = parameterMap.get(ATT_VALUE_PARAM);
		
		super.doOperationOnElement(elem);
	}
	
	
	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements(ATT_VALUE_PARAM);
		}
		
		return parentValidationRules;
	}


}
