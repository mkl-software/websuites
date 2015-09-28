package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.navigation.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkElementInnerHTML", argumentTypes = {String.class, String.class})
public class CheckElementInnerHtmlCommand extends OperationOnWebElement {

	protected String expectedInnerHTML;
	
	protected String actualInnerHTML;
	
	
	public CheckElementInnerHtmlCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}


	@SuppressWarnings("serial")
	public CheckElementInnerHtmlCommand(final String selector, final String expectedText) {
		super(new HashMap<String, String>() {{
			put("css", selector);
			put("innerHTML", expectedText);
		}});
	}
	
	
	
	protected class CheckElementInnerHTML extends AbstractSingleStringCheck {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner HTML in the web page element with selector '%s'"
						+ " to be exactly '%s', but was '%s'", by, expectedInnerHTML, elementText)
				.isEqualTo(expectedInnerHTML);
		}
		
		@Override
		protected String getStringParam() {
			return actualInnerHTML;
		}
	}
	
	
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		actualInnerHTML = elem.getAttribute("innerHTML");
		
		// not all browsers may support innerHTML attribute on WebElement level, so
		// use Javascript invocation then instead:
		if (actualInnerHTML == null) {
			
			actualInnerHTML = (String)((JavascriptExecutor) browser)
					.executeScript("return arguments[0].innerHTML;", elem);
		}
		
		AbstractCheck checkLogic = defineCheckLogic();
		
		expectedInnerHTML = parameterMap.get("innerHTML");
		
		checkLogic.runStandardCommand();
		
		
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementInnerHTML();
	}


	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements("innerHTML");
		}
		
		return parentValidationRules;
	}

}
