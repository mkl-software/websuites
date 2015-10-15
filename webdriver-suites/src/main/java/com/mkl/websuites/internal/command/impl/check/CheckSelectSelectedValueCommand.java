package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectedValue", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedValueCommand extends CheckSelectSelectedTextCommand {


	static {
		SELECTED_TEXT_PARAM = "value";
	}
	
	public CheckSelectSelectedValueCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckSelectSelectedValueCommand(String selector, String expectedText) {
		super(selector, expectedText);
	}

	
	protected class CheckSelectSelectedValue extends CheckSelectSelectedText {


		protected String getOptionString(WebElement elem) {
			return elem.getAttribute("value");
		}


		@Override
		protected void runSingleStringAssertion(StringAssert assertion, String string) {
			
			assertion
				.overridingErrorMessage("Expecting option with id '%s' to be selected  in the SELECT element picked "
						+ "by selector '%s'", expectedSelectText, by)
				.isNotEmpty();
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckSelectSelectedValue();
	}
	
}
