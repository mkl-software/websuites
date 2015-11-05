package com.mkl.websuites.internal.command;

import java.util.List;

import junit.framework.Test;

public interface CommandTestConverter {

    List<Test> convertCommandsToTests(List<Command> parsedCommands, String scenarioFileName);

}
