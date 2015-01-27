package com.mkl.websuites.internal.command;

import static java.util.Arrays.asList;
import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestSuite;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mockit.Deencapsulation;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.mkl.websuites.internal.command.impl.flow.RepeatControlFlowHandler;
import com.mkl.websuites.test.unit.scenario.cmd.SampleCommand;


@RunWith(JUnitParamsRunner.class)
public class StandardCommandTestConverterTest {

	private StandardCommandTestConverter sut = new StandardCommandTestConverter();
	
	@Test
	public void shouldDoPlainConversionForNoConstrolFlowCommands() {
		//given
		Command command = new SampleCommand("");
		List<Command> commands = Arrays.asList(command, command, command);
		//when
		List<junit.framework.Test> convertedTests = sut.convertCommandsToTests(commands, "");
		//then
		assertThat(convertedTests).hasSize(1);
	}
	
	
	
	@Test
	public void shoulDoConversionForOneSubtestControlFlowCommandWihoutNested() {
		//given
		Command command = new SampleCommand("");
		Map<String, String> params = new HashMap<String, String>();
		params.put("subtest", "true");
		RepeatControlFlowHandler repeat = new RepeatControlFlowHandler(params);
		List<Command> commands = Arrays.asList(command, repeat, command, command);
		//when
		List<junit.framework.Test> convertedTests = sut.convertCommandsToTests(commands, "");
		//then
		assertThat(convertedTests).hasSize(1); // one master suite for all inner test definitions
		assertThat(convertedTests.get(0)).isInstanceOf(TestSuite.class);
		TestSuite masterSuite = (TestSuite) convertedTests.get(0);
		List<junit.framework.Test> innerTestsInsideSuiteScenario = Collections.list(masterSuite.tests());
		// should be: test for before-repeat commands -> empty suite for repeat subtests ->
		// test for after repeat commands:
		assertThat(innerTestsInsideSuiteScenario).hasSize(3);
		assertThat(innerTestsInsideSuiteScenario.get(0))
			.isInstanceOf(junit.framework.Test.class)
			.isNotInstanceOf(TestSuite.class);
		assertThat(innerTestsInsideSuiteScenario.get(1)).isExactlyInstanceOf(TestSuite.class);
		assertThat(innerTestsInsideSuiteScenario.get(2))
			.isInstanceOf(junit.framework.Test.class)
			.isNotInstanceOf(TestSuite.class);
	}
	
	
	
	@Parameters(method = "provideCommandListsWithNoSubtests")
	@Test
	public void shouldNotContainSubtest(List<Command> inputList) {
		//given inputList
		//when
		sut.checkRecursivelyForSubtests(inputList);
		boolean containsSubtests = Deencapsulation.getField(sut, "hasSubtests");
		//then
		assertThat(containsSubtests).isFalse();
	}
	
	
	@Parameters(method = "provideCommandListsWithSubtests")
	@Test
	public void shouldContainSubtest(List<Command> inputList) {
		//given inputList
		//when
		sut.checkRecursivelyForSubtests(inputList);
		boolean containsSubtests = Deencapsulation.getField(sut, "hasSubtests");
		//then
		assertThat(containsSubtests).isTrue();
	}
	
	
	
	@SuppressWarnings({ "unused", "serial" })
	private Object[] provideCommandListsWithNoSubtests() {
		return $(
				$(asList()),
				$(asList((Command) new SampleCommand(""), new SampleCommand(""), new SampleCommand(""))),
				$(asList((Command) new SampleCommand(""), new SampleCommand(""), new RepeatControlFlowHandler())),
				$(asList(new RepeatControlFlowHandler(new HashMap<String, String>() {{
					put("subtest", "false");
				}
				})))
		);
	}
	
	
	
	@SuppressWarnings({ "unused", "serial" })
	private Object[] provideCommandListsWithSubtests() {
		
		RepeatControlFlowHandler parentCommand = new RepeatControlFlowHandler(new HashMap<String, String>() {{
			put("subtest", "false");
		}});
		
		RepeatControlFlowHandler nested = new RepeatControlFlowHandler(new HashMap<String, String>() {{
			put("subtest", "true");
		}});
		
		parentCommand.setNestedCommands(asList((Command) nested));
		
		return $(
				$(asList(parentCommand)),
				$(asList(new RepeatControlFlowHandler(new HashMap<String, String>() {{
					put("subtest", "true");
				}})))
				);
	}
}
