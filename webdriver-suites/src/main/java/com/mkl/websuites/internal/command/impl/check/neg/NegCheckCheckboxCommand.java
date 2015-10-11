package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckCheckboxCommand;


@CommandDescriptor(name = "~checkCheckbox", argumentTypes = {String.class, String.class})
public class NegCheckCheckboxCommand extends CheckCheckboxCommand {

	public NegCheckCheckboxCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckCheckboxCommand(String selector, String expectedText) {
		super(selector, expectedText);
	}

	
	protected class NegCheckCheckBox extends CheckCheckBox {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting checkbox selected by selector '%s'"
						+ " NOT to be " + (expectedCheckedValue.equalsIgnoreCase("true") ? "checked" : "unchecked"), by)
				.isNotEqualTo((expectedCheckedValue));
		}
	}
	
	
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckCheckBox();
	}
}
