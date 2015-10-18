package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.BooleanParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;



@CommandDescriptor(name = "checkCheckbox", argumentTypes = {String.class, String.class})
public class CheckCheckboxCommand extends OperationOnWebElement {

	
	private static final String CHECKED_PARAM = "checked";

	protected String expectedCheckedValue;
	
	protected String actualCheckedValue;
	
	
	
	public CheckCheckboxCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	
	@SuppressWarnings("serial")
	public CheckCheckboxCommand(final String selector, final String expectedText) {
		super(new HashMap<String, String>() {{
			put("css", selector);
			put(CHECKED_PARAM, expectedText);
		}});
	}
	
	
	
	protected class CheckCheckBox extends AbstractSingleStringCheck {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting checkbox selected by selector '%s'"
						+ " to be " + (expectedCheckedValue.equalsIgnoreCase("true") ? "checked" : "unchecked"), by)
				.isEqualToIgnoringCase((expectedCheckedValue));
		}
		
		@Override
		protected String getStringParam() {
			return actualCheckedValue;
		}
	}
	
	
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		if (!(elem.getTagName().equalsIgnoreCase("input") &&
			elem.getAttribute("type").equalsIgnoreCase("checkbox"))) {
			fail("Element expected to be a checkbox");
		}
		
		actualCheckedValue = elem.getAttribute(CHECKED_PARAM);
		
		actualCheckedValue = actualCheckedValue == null ? "false" : actualCheckedValue;
		
		AbstractCheck checkLogic = defineCheckLogic();
		
		expectedCheckedValue = parameterMap.get(CHECKED_PARAM);
		
		checkLogic.runStandardCommand();
		
		
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckCheckBox();
	}


	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements(CHECKED_PARAM);
		}
		
		return parentValidationRules;
	}
	
	
	
	@Override
	protected List<ParameterValueValidator> defineParameterValueValidators() {
		
		List<ParameterValueValidator> parameterValueValidators = super.defineParameterValueValidators();
		
		parameterValueValidators.add(new BooleanParamValidator(CHECKED_PARAM));
		
		return parameterValueValidators;
	}

}
