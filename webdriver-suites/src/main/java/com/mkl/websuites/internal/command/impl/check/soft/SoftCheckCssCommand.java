package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckCssCommand;


@CommandDescriptor(name = "softCheckCss", argumentTypes = {String.class, String.class, String.class})
public class SoftCheckCssCommand extends CheckCssCommand {

	public SoftCheckCssCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckCssCommand(String selector, String cssAttName,
			String expectedCssValue) {
		super(selector, cssAttName, expectedCssValue);
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckCss() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
