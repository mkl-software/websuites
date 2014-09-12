package com.mkl.websuites.internal.services;

import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.impl.BrowserControllerImpl;
import com.mkl.websuites.internal.impl.ConfigurationManagerImpl;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessorImpl;
import com.mkl.websuites.internal.services.ServiceDefinition.Service;



@ServiceDefinition({
	
	@Service(
		service = BrowserController.class,
		implementation = BrowserControllerImpl.class
	),
	
	@Service(
			service = ConfigurationManager.class,
			implementation = ConfigurationManagerImpl.class
	),
	@Service(
			service = ScenarioFileProcessor.class,
			implementation = ScenarioFileProcessorImpl.class
	)
})
public class DefaultServiceDefinitions {

}
