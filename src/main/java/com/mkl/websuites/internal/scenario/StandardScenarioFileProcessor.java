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
package com.mkl.websuites.internal.scenario;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.internal.command.CommandParser;
import com.mkl.websuites.internal.command.CommandPostProcessor;
import com.mkl.websuites.internal.command.CommandTestConverter;
import com.mkl.websuites.internal.services.ServiceFactory;



public class StandardScenarioFileProcessor implements ScenarioFileProcessor {



    private static ScenarioFileProcessor instance = new StandardScenarioFileProcessor();

    public static ScenarioFileProcessor getInstance() {
        return instance;
    }



    @Override
    public List<Test> processSingleScenarioFile(final String scenarioFileName) {

        File scenarioFile = new File(scenarioFileName);

        if (!scenarioFile.exists()) {

            return failToLoadScenario(scenarioFileName);
        }

        List<SourceLine> preprocessedScenarioFile =
                ServiceFactory.get(ScenarioFilePreprocessor.class).preprocessScenarioFile(scenarioFile);

        List<Command> parsedCommands =
                ServiceFactory.get(CommandParser.class).parseCommandFromFile(preprocessedScenarioFile);

        List<Command> postProcessedCommands =
                ServiceFactory.get(CommandPostProcessor.class).postProcessCommands(parsedCommands);

        List<Test> convertedCommandsToTests =
                ServiceFactory.get(CommandTestConverter.class).convertCommandsToTests(postProcessedCommands,
                        scenarioFileName);

        return convertedCommandsToTests;

    }



    private List<Test> failToLoadScenario(final String scenarioFileName) {

        Test failed = new TestCase() {
            @Override
            protected void runTest() throws Throwable {
                fail("Scenario file " + scenarioFileName + " doesn't exist.");
            }

            @Override
            public String getName() {
                return "Scenario file check";
            }
        };

        return Arrays.asList(failed);
    }
}
