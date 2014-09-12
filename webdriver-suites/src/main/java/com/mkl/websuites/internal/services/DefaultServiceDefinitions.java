package com.mkl.websuites.internal.services;

import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.StandardConfigurationManager;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.browser.StandardBrowserController;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.scenario.StandardScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceDefinition.Service;



@ServiceDefinition({
	
	@Service(
		service = BrowserController.class,
		implementation = StandardBrowserController.class
	),
	
	@Service(
			service = ConfigurationManager.class,
			implementation = StandardConfigurationManager.class
	),
	@Service(
			service = ScenarioFileProcessor.class,
			implementation = StandardScenarioFileProcessor.class
	)
})
public class DefaultServiceDefinitions {

}
