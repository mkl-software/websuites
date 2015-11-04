package com.mkl.websuites.internal.command.impl.wait;

import java.util.concurrent.TimeUnit;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "setImplicitWaitTimeout", argumentTypes = Integer.class)
public class SetImplicitWaitTimeoutCommand extends BaseCommand {

	
	private int newTimeout;
	
	public SetImplicitWaitTimeoutCommand(Integer newTimeout) {
		super();
		this.newTimeout = newTimeout;
	}

	@Override
	protected void runStandardCommand() {
		browser.manage().timeouts().implicitlyWait(newTimeout, TimeUnit.SECONDS);
	}

}
