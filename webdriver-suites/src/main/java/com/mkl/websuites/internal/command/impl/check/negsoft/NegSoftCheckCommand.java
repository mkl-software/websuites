package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckUtils;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckCommand;


@CommandDescriptor(name = "~softCheck", argumentTypes = String.class)
public class NegSoftCheckCommand extends NegCheckCommand {

	public NegSoftCheckCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckCommand(String elemement) {
		super(elemement);
	}
	
	@Override
	protected void fail(String message) {
		CheckUtils.softFail(softly, message);
	}
	

}
