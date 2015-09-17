package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckCommand;


@CommandDescriptor(name = "~check", argumentTypes = String.class)
public class NegCheckCommand extends CheckCommand {

	public NegCheckCommand(String elemement) {
		super(elemement);
	}
	
	
	public NegCheckCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}



	@Override
	protected void runCommandWithParameters() {
		
		boolean fail = false;
		try {
			super.runCommandWithParameters();
			fail = true;
			
		} catch (AssertionError e) {
			// if "no element found", then passed, else propagate:
			if (!e.getMessage().contains("No element found")) {
				throw e;
			}
			
		} catch (Exception e) {
			throw new AssertionError(e);
		}
		
		if (fail) {
			fail("Element with selector [" + by + "] was found on the page, but shouldn't be.");
		}
	}

}
