package com.mkl.websuites.internal.command;

import java.util.List;

import com.mkl.websuites.command.Command;

import junit.framework.Test;

public interface CommandTestConverter {

    List<Test> convertCommandsToTests(List<Command> parsedCommands, String scenarioFileName);

}
