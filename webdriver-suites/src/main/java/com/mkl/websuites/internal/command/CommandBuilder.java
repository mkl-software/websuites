package com.mkl.websuites.internal.command;

import com.mkl.websuites.internal.scenario.SourceLine;

public interface CommandBuilder {

    Command instantiateCommand(String commandName, String[] arguments, SourceLine sourceLine);

}
