package com.mkl.websuites.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.test.unit.scenario.CommandBuilderTest;
import com.mkl.websuites.test.unit.scenario.ControlFlowProcessingTest;
import com.mkl.websuites.test.unit.scenario.ScenarioFileProcessorTest;
import com.mkl.websuites.test.unit.scenario.ScenarioPreprocessingTest;
import com.mkl.websuites.test.unit.scenario.flows.RepeatDetailedIntegrationTest;



@RunWith(Suite.class)
@SuiteClasses({
	ScenarioPreprocessingTest.class,
	ScenarioFileProcessorTest.class,
	CommandBuilderTest.class,
	ControlFlowProcessingTest.class,
	RepeatDetailedIntegrationTest.class
})
public class ScenarioTests {

}
