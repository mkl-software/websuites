package com.mkl.websuites.internal.command;

import java.util.List;

public interface CommandPostProcessor {

    List<Command> postProcessCommands(List<Command> parsedCommands);

}
