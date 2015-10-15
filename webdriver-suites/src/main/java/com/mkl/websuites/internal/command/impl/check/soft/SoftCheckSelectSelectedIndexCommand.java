package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedIndexCommand;


@CommandDescriptor(name = "softCheckSelectedIndex", argumentTypes = {String.class, String.class})
public class SoftCheckSelectSelectedIndexCommand extends
		CheckSelectSelectedIndexCommand {

	public SoftCheckSelectSelectedIndexCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckSelectSelectedIndexCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckSelectSelectedIndex() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
