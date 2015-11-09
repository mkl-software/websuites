package com.mkl.websuites.internal.command;

import java.util.List;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.internal.scenario.SourceLine;

public interface CommandParser {

    List<Command> parseCommandFromFile(List<SourceLine> preprocessedScenarioFile);

}
