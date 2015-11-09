package com.mkl.websuites.internal.command;

import java.util.List;

import com.mkl.websuites.command.Command;

public interface CommandPostProcessor {

    List<Command> postProcessCommands(List<Command> parsedCommands);

}
