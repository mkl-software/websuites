package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckCssCommand;


@CommandDescriptor(name = "~softCheckCss", argumentTypes = {String.class, String.class, String.class})
public class NegSoftCheckCssCommand extends NegCheckCssCommand {

	public NegSoftCheckCssCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckCssCommand(String selector, String cssAttName,
			String expectedCssValue) {
		super(selector, cssAttName, expectedCssValue);
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckCss() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
