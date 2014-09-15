package com.mkl.websuites.internal.services;

import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.StandardConfigurationManager;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.browser.StandardBrowserController;
import com.mkl.websuites.internal.command.CommandBuilder;
import com.mkl.websuites.internal.command.CommandParser;
import com.mkl.websuites.internal.command.CommandPostProcessor;
import com.mkl.websuites.internal.command.CommandTestConverter;
import com.mkl.websuites.internal.command.StandardCommandBuilder;
import com.mkl.websuites.internal.command.StandardCommandParser;
import com.mkl.websuites.internal.command.StandardCommandPostProcessor;
import com.mkl.websuites.internal.command.StandardCommandTestConverter;
import com.mkl.websuites.internal.scenario.ScenarioFilePreprocessor;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.scenario.StandardScenarioFilePreprocessor;
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
	),
	@Service(
			service = ScenarioFilePreprocessor.class,
			implementation = StandardScenarioFilePreprocessor.class
	),
	@Service(
			service = CommandParser.class,
			implementation = StandardCommandParser.class
	),
	@Service(
			service = CommandBuilder.class,
			implementation = StandardCommandBuilder.class
	),
	@Service(
			service = CommandPostProcessor.class,
			implementation = StandardCommandPostProcessor.class
	),
	@Service(
			service = CommandTestConverter.class,
			implementation = StandardCommandTestConverter.class
	)
})
public class DefaultServiceDefinitions {

}
