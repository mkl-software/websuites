package com.mkl.websuites.internal.command;

import java.util.List;

public interface CommandParser {

	List<Command> parseCommandFromFile(List<String> preprocessedScenarioFile);

}
