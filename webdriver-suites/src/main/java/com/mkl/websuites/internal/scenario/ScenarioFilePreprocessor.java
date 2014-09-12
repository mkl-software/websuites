package com.mkl.websuites.internal.scenario;

import java.io.File;
import java.util.List;

public interface ScenarioFilePreprocessor {

	List<String> preprocessScenarioFile(File scenarioFile);

}
