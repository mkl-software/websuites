package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ObjectArrayAssert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.navigation.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;



@CommandDescriptor(name = "checkSelectOptionText", argumentTypes = {String.class, String.class})
public class CheckSelectOptionTextCommand extends OperationOnWebElement {

	protected static String SELECTED_TEXT_PARAM = "text";

	protected String expectedSelectText;
	
	protected List<WebElement> selectOptions;

	protected List<WebElement> allSelectedOptions;
	
	
	
	public CheckSelectOptionTextCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	
	@SuppressWarnings("serial")
	public CheckSelectOptionTextCommand(final String selector, final String expectedText) {
		super(new HashMap<String, String>() {{
			put("css", selector);
			put(SELECTED_TEXT_PARAM, expectedText);
		}});
	}
	
	
	
	protected class CheckSelectOptionText extends AbstractCheck {

		@Override
		protected Object[] getAssertionsParameters() {
			return selectOptions.toArray();
		}

		@Override
		protected AbstractAssert<?, ?> buildAssertion(Object... args) {

			return assertThat(args);
		}

		@Override
		protected void runAssertion(AbstractAssert<?, ?> assertion,
				Object... args) {

			((ObjectArrayAssert<?>) assertion)
				.extracting("text")
				.overridingErrorMessage("Expecting SELECT element picked by selector '%s'"
						+ " to have text '%s' in one of its options", by, expectedSelectText)
				.contains(expectedSelectText);
		}
	}
	
	
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		if (!(elem.getTagName().equalsIgnoreCase("select"))) {
			fail("Element expected to be a SELECT");
		}
		
		Select select = new Select(elem);
		
		selectOptions = select.getOptions();
		
		allSelectedOptions = select.getAllSelectedOptions();
		
		AbstractCheck checkLogic = defineCheckLogic();
		
		expectedSelectText = parameterMap.get(SELECTED_TEXT_PARAM);
		
		checkLogic.runStandardCommand();
		
		
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckSelectOptionText();
	}


	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements(SELECTED_TEXT_PARAM);
		}
		
		return parentValidationRules;
	}

}
