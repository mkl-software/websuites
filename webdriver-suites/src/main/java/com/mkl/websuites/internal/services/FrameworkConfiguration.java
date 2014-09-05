package com.mkl.websuites.internal.services;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.impl.BrowserControllerImpl;
import com.mkl.websuites.internal.impl.ConfigurationManagerImpl;

import se.jbee.inject.bind.BinderModule;

public class FrameworkConfiguration extends BinderModule {

	@Override
	protected void declare() {
			
		bind(BrowserController.class).to(BrowserControllerImpl.getInstance());
		
		bind(ConfigurationManager.class).to(ConfigurationManagerImpl.getInstance());
	}

}
