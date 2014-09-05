package com.mkl.websuites.internal.services;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.impl.BrowserControllerImpl;

import se.jbee.inject.bind.BinderModule;

public class FrameworkConfiguration extends BinderModule {

	@Override
	protected void declare() {
			
		bind(BrowserController.class).to(BrowserControllerImpl.class);
	}

}
