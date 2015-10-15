package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectedText", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedTextCommand extends CheckSelectOptionTextCommand {

	
	public CheckSelectSelectedTextCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckSelectSelectedTextCommand(String selector, String expectedText) {
		super(selector, expectedText);
	}

	
	protected class CheckSelectSelectedText extends AbstractSingleStringCheck {


		@Override
		protected String getStringParam() {
			for (WebElement elem : allSelectedOptions) {
				String text = getOptionString(elem);
				if (text != null && predicate(text)) {
					return text;
				}
			}
			return "";
		}

		protected String getOptionString(WebElement elem) {
			return elem.getText();
		}

		protected boolean predicate(String text) {
			return text.equals(expectedSelectText);
		}
		


		@Override
		protected void runSingleStringAssertion(StringAssert assertion, String string) {
			
			assertion
				.overridingErrorMessage("Expecting selected option '%s' in the SELECT element picked by selector '%s'",
						expectedSelectText, by)
				.isNotEmpty();
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckSelectSelectedText();
	}
}
