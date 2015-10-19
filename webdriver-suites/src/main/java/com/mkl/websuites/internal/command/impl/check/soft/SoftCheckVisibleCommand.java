package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckVisibleCommand;


@CommandDescriptor(name = "softCheckVisible", argumentTypes = {String.class})
public class SoftCheckVisibleCommand extends CheckVisibleCommand {

	public SoftCheckVisibleCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckVisibleCommand(String selector) {
		super(selector);
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckVisible() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
