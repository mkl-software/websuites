/**
 * Copyright 2015 MKL Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mkl.websuites.internal.services;

import com.mkl.websuites.config.Service;
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



@ServiceDefinition({

@Service(service = BrowserController.class, implementation = StandardBrowserController.class),

@Service(service = ScenarioFileProcessor.class, implementation = StandardScenarioFileProcessor.class),
        @Service(service = ScenarioFilePreprocessor.class, implementation = StandardScenarioFilePreprocessor.class),
        @Service(service = CommandParser.class, implementation = StandardCommandParser.class),
        @Service(service = CommandBuilder.class, implementation = StandardCommandBuilder.class),
        @Service(service = CommandPostProcessor.class, implementation = StandardCommandPostProcessor.class),
        @Service(service = CommandTestConverter.class, implementation = StandardCommandTestConverter.class)})
public class DefaultServiceDefinitions {

}
