package com.mkl.websuites.internal.command.impl.flow.repeat;

import java.util.List;

import com.mkl.websuites.command.Command;

public interface RepeatHandler {


    void doRepeat(List<Command> nestedCommands);
}
