package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.navigation.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkElementText", argumentTypes = {String.class, String.class})
public class CheckElementTextCommand extends OperationOnWebElement {
	
	
	protected String expectedText;
	
	
	protected class CheckElementText extends AbstractSingleStringCheck {
		
		protected String elementText;
		
		public CheckElementText(String elementText) {
			this.elementText = elementText;
		}

		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner text of web page element with selector '%s'"
						+ "to be exactly '%s', but was '%s'", by, expectedText, elementText)
				.isEqualTo(expectedText);
		}
		
		@Override
		protected String getStringParam() {
			return elementText;
		}
	}
	
	
	@SuppressWarnings("serial")
	public CheckElementTextCommand(final String selector, final String expectedText) {
		super(new HashMap<String, String>() {{
			put("css", selector);
			put("text", expectedText);
		}});
	}


	
	
	/**
	 * Workaround for situation where this class should inherit both from AbstractCheck and OperationOnWebElement.
	 * @param webElement 
	 * @return
	 */
	protected AbstractCheck defineCheckLogic(final WebElement webElement) {
		
		return new CheckElementText(webElement.getText());
	}

	public CheckElementTextCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		AbstractCheck checkLogic = defineCheckLogic(elem);
		
		expectedText = parameterMap.get("text");
		
		checkLogic.runStandardCommand();
		
		
	}
	
	
	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements("text");
		}
		
		return parentValidationRules;
	}

}
