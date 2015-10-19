package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckHiddenCommand;


@CommandDescriptor(name = "~checkHidden", argumentTypes = {String.class})
public class NegCheckHiddenCommand extends CheckHiddenCommand {

	public NegCheckHiddenCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckHiddenCommand(String selector) {
		super(selector);
	}
	
	protected class NegCheckHidden extends CheckHidden {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String displayValue) {
			
			assertion
				.overridingErrorMessage("Expecting web page element with selector '%s'"
						+ " NOT to have property 'display: none;'", by)
				.isNotEqualTo("none");
		}
		
		@Override
		protected String getStringParam() {
			return foundElement.getCssValue("display");
		}
	}
	
	
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckHidden();
	}

}
