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
package com.mkl.websuites.internal.command;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.VisibleForTesting;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.command.Command;
import com.mkl.websuites.internal.command.impl.flow.ControlFlowHandler;
import com.mkl.websuites.internal.command.impl.flow.RepeatControlFlowHandler;
import com.mkl.websuites.internal.command.impl.flow.Subtestable;



@Slf4j
public class StandardCommandTestConverter implements CommandTestConverter {



    // private List<Command> inputCommandList;

    private String masterScenarioFileName;

    private boolean hasSubtests;


    private static StandardCommandTestConverter instance = new StandardCommandTestConverter();


    public static StandardCommandTestConverter getInstance() {
        return instance;
    }



    @Override
    public List<Test> convertCommandsToTests(final List<Command> parsedCommands, final String scenarioFileName) {

        // this.inputCommandList = parsedCommands;
        this.masterScenarioFileName = scenarioFileName;

        List<Test> convertedTests;

        if (!containsSubtests(parsedCommands)) {

            convertedTests = convertWtihoutSubtests(parsedCommands);
        } else {

            convertedTests = convertForSubtests(parsedCommands);
        }

        return convertedTests;
    }



    /**
     * When a REPEAT statement is configured for subtests, then all DDL params in the REPEAT should
     * be rendered inside seperate JUnit test cases. But then all commands before and after the
     * REPEAT should also be put inside test cases. There could also be nested REPEAT-subtest
     * statements, so finally the test case tree might become really complex.
     * 
     * @param parsedCommands
     * @return
     */
    private List<Test> convertForSubtests(List<Command> inputCommandList) {

        // for now implemented for ONE LEVEL, without recursion! TODO: Add recursion too.

        List<Test> resultTestList = new ArrayList<Test>();

        // TODO: not needed? master suite is scenario suite...
        TestSuite mainScenarioTopLevelSuit = new TestSuite(); 
        
        mainScenarioTopLevelSuit.setName("Master suite for subtest");
        resultTestList.add(mainScenarioTopLevelSuit);


        List<Test> thisLevelTests = new ArrayList<Test>();

        List<Command> commandsBufferBetweenRepeats = new ArrayList<Command>();

        for (Command command : inputCommandList) {

            if (!(command instanceof RepeatControlFlowHandler)) { // TODO: consider other cases

                commandsBufferBetweenRepeats.add(command);

            } else {

                RepeatControlFlowHandler repeatCommand = (RepeatControlFlowHandler) command;

                if (repeatCommand.isSubtest()) {

                    if (!commandsBufferBetweenRepeats.isEmpty()) {
                        thisLevelTests.add(newTestCase(commandsBufferBetweenRepeats));
                        commandsBufferBetweenRepeats.clear();
                    }

                    TestSuite repeatSuite = new TestSuite();
                    repeatSuite.setName(repeatCommand.getSubtestName());

                    int idx = 0;
                    for (String paramTestCase : repeatCommand.getSubTestCaseNames()) {

                        repeatSuite.addTest(newDdlParamTestCase(repeatCommand, paramTestCase, idx++));
                    }
                    thisLevelTests.add(repeatSuite);

                } else {

                    commandsBufferBetweenRepeats.add(command);
                }

            }
        }

        if (!commandsBufferBetweenRepeats.isEmpty()) {
            thisLevelTests.add(newTestCase(commandsBufferBetweenRepeats));
        }

        for (Test test : thisLevelTests) {
            mainScenarioTopLevelSuit.addTest(test);
        }

        return resultTestList;
    }



    private Test newDdlParamTestCase(final RepeatControlFlowHandler repeatCommand, final String paramTestCase,
            final int paramIndex) {

        return new TestCase() {

            @Override
            public String getName() {

                return paramTestCase;
            }

            @Override
            protected void runTest() throws Throwable {

                repeatCommand.runForDDlParam(paramIndex);
            }
        };
    }



    private Test newTestCase(final List<Command> innerCommands) {

        final String fromName = innerCommands.get(0).toString();
        final String toName = innerCommands.get(innerCommands.size() - 1).toString();

        final List<Command> localInnerCommands = new ArrayList<Command>();
        localInnerCommands.addAll(innerCommands);

        return new TestCase() {

            @Override
            public String getName() {
                return "commands [" + fromName + "] -> [" + toName + "]";
            }

            @Override
            protected void runTest() throws Throwable {

                for (Command command : localInnerCommands) {
                    command.run();
                }
            }
        };
    }



    private boolean containsSubtests(List<Command> inputCommandList) {

        hasSubtests = false;

        checkRecursivelyForSubtests(inputCommandList);

        return hasSubtests;
    }



    @VisibleForTesting
    protected void checkRecursivelyForSubtests(List<Command> commands) {

        for (Command command : commands) {

            if (command instanceof Subtestable && ((Subtestable) command).isSubtest()) {
                hasSubtests = true;
                break;
            }

            if (command instanceof ControlFlowHandler) {

                checkRecursivelyForSubtests(((ControlFlowHandler) command).getNestedCommands());

            }
        }
    }



    /**
     * Without subtests there is just one JUnit test case for all commands inside this scenario
     * file.
     * 
     * @return
     */
    private List<Test> convertWtihoutSubtests(final List<Command> inputCommandList) {

        List<Test> tests = new ArrayList<Test>();

        final String currentlyDefiningBrowser = WebSuitesRunner.getCurrentlyDefiningBrowser();

        final String testName = buildTestName() + " [" + currentlyDefiningBrowser + "]";

        final List<Command> localCommandsToRunList = new ArrayList<Command>();
        localCommandsToRunList.addAll(inputCommandList);

        Test test = new TestCase() {


            @Override
            public String getName() {
                return testName;
            }

            @Override
            protected void runTest() throws Throwable {

                for (Command command : localCommandsToRunList) {

                    log.debug("running command from " + command);
                    command.run();

                }
            }
        };

        tests.add(test);

        return tests;
    }



    protected String buildTestName() {
        // TODO: make it configurable how paths are displayed
        Path path = Paths.get(masterScenarioFileName);
        return path.isAbsolute() ? path.getName(path.getNameCount() - 1).toString() : masterScenarioFileName;
    }

}
