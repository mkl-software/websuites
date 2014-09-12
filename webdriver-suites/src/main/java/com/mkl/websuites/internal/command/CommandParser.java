package com.mkl.websuites.internal.command;

import java.io.File;
import java.util.List;

public interface CommandParser {

	List<Command> parseCommandFromFile(File scenarioFile);

}
