package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectedOptionTextCommand;



@CommandDescriptor(name = "softCheckSelectOptionText", argumentTypes = {String.class, String.class})
public class SoftCheckSelectedOptionTextCommand extends
		CheckSelectedOptionTextCommand {

	public SoftCheckSelectedOptionTextCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckSelectedOptionTextCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckCheckBox() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return softly.assertThat(args);
			}
		};
	}
}
