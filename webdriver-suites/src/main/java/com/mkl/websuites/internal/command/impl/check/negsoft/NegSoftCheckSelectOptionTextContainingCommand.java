package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSelectOptionTextContainingCommand;


@CommandDescriptor(name = "~softCheckSelectOptionTextContaining", argumentTypes = {String.class, String.class})
public class NegSoftCheckSelectOptionTextContainingCommand extends
		NegCheckSelectOptionTextContainingCommand {

	public NegSoftCheckSelectOptionTextContainingCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckSelectOptionTextContainingCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckSelectOptionTextContaining() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
