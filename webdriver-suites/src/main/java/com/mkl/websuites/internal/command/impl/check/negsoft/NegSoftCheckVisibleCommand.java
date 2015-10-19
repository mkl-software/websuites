package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckVisibleCommand;


@CommandDescriptor(name = "~softCheckVisible", argumentTypes = {String.class})
public class NegSoftCheckVisibleCommand extends NegCheckVisibleCommand {

	public NegSoftCheckVisibleCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckVisibleCommand(String selector) {
		super(selector);
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckVisible() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
