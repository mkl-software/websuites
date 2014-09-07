package com.mkl.websuites.internal.services;

import com.mkl.websuites.ext.ServiceDefinition;
import com.mkl.websuites.ext.ServiceDefinition.Service;
import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.impl.BrowserControllerImpl;
import com.mkl.websuites.internal.impl.ConfigurationManagerImpl;



@ServiceDefinition({
	
	@Service(
		service = BrowserController.class,
		implementation = BrowserControllerImpl.class
	),
	
	@Service(
			service = ConfigurationManager.class,
			implementation = ConfigurationManagerImpl.class
			)
})
public class DefaultServiceDefinitions {

}
