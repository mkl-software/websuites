package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkCssClass", argumentTypes = {String.class, String.class})
public class CheckCssClassCommand extends OperationOnWebElement {

	
	protected static final String CSS_CLASS_ATTR = "class";

	protected String actualCssClassNames;
	protected String expectedCssClassName;

	
	public CheckCssClassCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}


	@SuppressWarnings("serial")
	public CheckCssClassCommand(final String selector, final String cssClassName) {
		super(new HashMap<String, String>() {{
			put("css", selector);
			put(CSS_CLASS_ATTR, cssClassName);
		}});
	}
	
	
	
	protected class CheckCssClass extends AbstractSingleStringCheck {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting web page element with selector '%s'"
						+ " to have a CSS class '%s', but it has classes '%s'", by, expectedCssClassName, actualCssClassNames)
				.contains(expectedCssClassName);
		}
		
		@Override
		protected String getStringParam() {
			return actualCssClassNames;
		}
	}
	
	
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		expectedCssClassName = parameterMap.get(CSS_CLASS_ATTR);
		
		actualCssClassNames = elem.getAttribute("class");
		
		AbstractCheck checkLogic = defineCheckLogic();
		
		checkLogic.runStandardCommand();
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckCssClass();
	}


	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements(CSS_CLASS_ATTR);
		}
		
		return parentValidationRules;
	}

}
