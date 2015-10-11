package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckCheckboxCommand;


@CommandDescriptor(name = "softCheckCheckbox", argumentTypes = {String.class, String.class})
public class SoftCheckCheckbox extends CheckCheckboxCommand {

	public SoftCheckCheckbox(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckCheckbox(String selector, String expectedText) {
		super(selector, expectedText);
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckCheckBox() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
