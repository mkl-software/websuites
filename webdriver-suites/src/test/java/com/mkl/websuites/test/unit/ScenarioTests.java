package com.mkl.websuites.test.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.internal.command.impl.flow.RepeatControlFlowHandlerTest;
import com.mkl.websuites.test.unit.scenario.CommandBuilderTest;
import com.mkl.websuites.test.unit.scenario.ControlFlowProcessingTest;
import com.mkl.websuites.test.unit.scenario.ScenarioFileProcessorTest;
import com.mkl.websuites.test.unit.scenario.ScenarioPreprocessingTest;



@RunWith(Suite.class)
@SuiteClasses({
	ScenarioPreprocessingTest.class,
	ScenarioFileProcessorTest.class,
	CommandBuilderTest.class,
	ControlFlowProcessingTest.class,
	RepeatControlFlowHandlerTest.class
})
public class ScenarioTests {

}
