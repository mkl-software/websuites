package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckCommand;


@CommandDescriptor(name = "softCheck", argumentTypes = String.class)
public class SoftCheckCommand extends CheckCommand {

	public SoftCheckCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckCommand(String elemement) {
		super(elemement);
		parameterMap = null;
	}
	
	
	@Override
	protected void fail(String message) {
		// no "fail(msg)" method in soft assertions :/ Workaround:
		softly.assertThat(false)
			.overridingErrorMessage(message)
			.isTrue();
	}

}
