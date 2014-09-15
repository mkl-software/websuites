package com.mkl.websuites.internal.command.impl;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "goto", argumentTypes = {String.class})
public class GotoCommand extends BaseCommand {

	
	private String address;

	 public GotoCommand(String address) {
		this.address = address;
	}

	@Override
	protected void runCommand() {
		if (!address.startsWith("http:")) {
			address = "http://" + address;
		}
		browser.get(address);
		
	}
	
	

}
