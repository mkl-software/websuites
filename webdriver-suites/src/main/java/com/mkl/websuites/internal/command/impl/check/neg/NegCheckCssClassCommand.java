package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckCssClassCommand;


@CommandDescriptor(name = "~checkCssClass", argumentTypes = {String.class, String.class})
public class NegCheckCssClassCommand extends CheckCssClassCommand {

	public NegCheckCssClassCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckCssClassCommand(String selector, String cssClassName) {
		super(selector, cssClassName);
	}

	
	protected class NegCheckCssClass extends CheckCssClass {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting web page element with selector '%s' NOT to have a CSS class '%s', "
						+ "but it has classes '%s'", by, expectedCssClassName, actualCssClassNames)
				.doesNotContain(expectedCssClassName);
		}
	}
	
	
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckCssClass();
	}
}
