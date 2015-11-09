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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.internal.scenario.SourceLine;
import com.mkl.websuites.internal.services.ServiceFactory;

public class StandardCommandParser implements CommandParser {

    private static final String SCENARIO_DELIMITER = "\t+";


    private static StandardCommandParser instance = new StandardCommandParser();

    public static StandardCommandParser getInstance() {
        return instance;
    }



    @Override
    public List<Command> parseCommandFromFile(List<SourceLine> preprocessedScenarioFile) {

        List<Command> commands = new ArrayList<>();

        for (SourceLine sourceLine : preprocessedScenarioFile) {

            Command command = buildCommand(sourceLine);

            if (command instanceof SourceInfoHolder) {
                ((SourceInfoHolder) command).setCommandSourceLine(sourceLine);
            }

            commands.add(command);
        }

        return commands;
    }



    protected Command buildCommand(SourceLine sourceLine) {

        String[] tokens = tokenizeLine(sourceLine.getLine());

        CommandBuilder commandManager = ServiceFactory.get(CommandBuilder.class);

        String commandName = tokens[0];

        String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);

        Command command = commandManager.instantiateCommand(commandName, arguments, sourceLine);

        return command;
    }



    protected String[] tokenizeLine(String line) {

        return line.split(SCENARIO_DELIMITER);
    }

}
