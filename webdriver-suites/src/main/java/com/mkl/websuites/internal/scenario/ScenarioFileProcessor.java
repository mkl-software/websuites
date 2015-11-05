package com.mkl.websuites.internal.scenario;

import java.util.List;

import junit.framework.Test;

public interface ScenarioFileProcessor {

    public abstract List<Test> processSingleScenarioFile(String scenarioFileName);

}
