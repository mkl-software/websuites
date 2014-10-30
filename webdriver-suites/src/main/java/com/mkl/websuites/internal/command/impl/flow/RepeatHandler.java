package com.mkl.websuites.internal.command.impl.flow;

import java.util.List;

import com.mkl.websuites.internal.command.Command;

public interface RepeatHandler {

	
	void doRepeat(List<Command> nestedCommands);
}
