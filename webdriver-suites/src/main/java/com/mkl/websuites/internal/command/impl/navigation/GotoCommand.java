package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.WebSuitesConfig_rename;
import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.services.ServiceFactory;


@CommandDescriptor(name = "goto", argumentTypes = {String.class})
public class GotoCommand extends BaseCommand {

	
	private String address;

	 public GotoCommand(String address) {
		this.address = address;
	}

	@Override
	protected void runStandardCommand() {
		
		if (address.startsWith("/")) {
			// relative address:
			WebSuitesConfig_rename config = ServiceFactory.get(ConfigurationManager.class).getConfiguration();
			// TODO: use a service to apply normalizePath logic
			address = config.basePath() + address;
			
		} else {
			if (!address.startsWith("http:")) {
				address = "http://" + address;
			}
		}
		
		browser.get(address);
		
	}

}
