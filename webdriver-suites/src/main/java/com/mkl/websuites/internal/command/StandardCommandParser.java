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
