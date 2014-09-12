package com.mkl.websuites.internal.command;

public interface CommandBuilder {

	Command instantiateCommand(String commandName, String[] arguments);

}
