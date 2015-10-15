package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSelectSelectedTextCommand;


@CommandDescriptor(name = "~softCheckSelectedText", argumentTypes = {String.class, String.class})
public class NegSoftCheckSelectSelectedTextCommand extends
		NegCheckSelectSelectedTextCommand {

	public NegSoftCheckSelectSelectedTextCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckSelectSelectedTextCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckSelectSelectedTextContaining() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
