package com.mkl.websuites.internal.command;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.mkl.websuites.internal.command.impl.flow.RepeatControlFlowHandler;
import com.mkl.websuites.test.unit.scenario.cmd.SampleCommand;


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
	
	
//	@Test
//	public void shoulDoConversionForOneSubtestControlFlowCommand() {
//		//given
//		Command command = new SampleCommand("");
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("subtest", "true");
//		Command repeat = new RepeatControlFlowHandler(params );
//		List<Command> commands = Arrays.asList(command, repeat, command, command);
//		//when
//		List<junit.framework.Test> convertedTests = sut.convertCommandsToTests(commands, "");
//		//then
//		assertThat(convertedTests).hasSize(3);
//	}
}
