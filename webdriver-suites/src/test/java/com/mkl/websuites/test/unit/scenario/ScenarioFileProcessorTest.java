package com.mkl.websuites.test.unit.scenario;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import mockit.Deencapsulation;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandParser;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.test.core.ServiceBasedTest;
import com.mkl.websuites.test.unit.scenario.cmd.MultiArgCommand;
import com.mkl.websuites.test.unit.scenario.cmd.NoArgCommand;
import com.mkl.websuites.test.unit.scenario.cmd.SampleCommand;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScenarioFileProcessorTest extends ServiceBasedTest {

	
	
	
	
	@Override
	protected Class<?> getServiceUnderTestClass() {
		return CommandParser.class;
	}


	
	
	@Test
	public void testTokenizingOneToken() {
		
		String line = "trimmed Value with spaces";
		
		String[] tokens = tokenize(line);
		
		assertEquals(1, tokens.length);
		
		assertEquals(line, tokens[0]);
	}
	
	
	@Test
	public void testTokenizingTwoTokens() {
		
		String line = "trimmed Value with spaces	second value";
		
		String[] tokens = tokenize(line);
		
		assertEquals(2, tokens.length);
		
		assertEquals("second value", tokens[1]);
	}
	
	
	
	@Test
	public void testTokenizingMultiTabs() {
		
		String line = "trimmed Value with spaces				"
				+ "second value		third with spaces			last";
		
		String[] tokens = tokenize(line);
		
		assertEquals(4, tokens.length);
		
		assertEquals("last", tokens[3]);
	}


	
	
	private String[] tokenize(String line) {
		
		CommandParser logic = ServiceFactory.get(CommandParser.class);
		String[] tokens = Deencapsulation.invoke(logic, "tokenizeLine", line);
		return tokens;
	}
	
	
	
	@Test
	public void testParseCommandFromFile1() {
		CommandParser logic = ServiceFactory.get(CommandParser.class);
		String[] lines = new String[] {
				
				"sample\tsample command one string arg",
				"noArg\t"
		};
		List<Command> parsedCommands = logic.parseCommandFromFile(Arrays.asList(lines));
		assertNotNull(parsedCommands);
		assertEquals(2, parsedCommands.size());
		assertThat(parsedCommands.get(0), instanceOf(SampleCommand.class));
		assertThat(parsedCommands.get(1), instanceOf(NoArgCommand.class));
	}
	
	
	@Test
	public void testParseCommandFromFile2() {
		CommandParser logic = ServiceFactory.get(CommandParser.class);
		String[] lines = new String[] {};
		List<Command> parsedCommands = logic.parseCommandFromFile(Arrays.asList(lines));
		assertNotNull(parsedCommands);
		assertEquals(0, parsedCommands.size());
	}
	
	@Test
	public void testParseCommandFromFile3() {
		CommandParser logic = ServiceFactory.get(CommandParser.class);
		String[] lines = new String[] {
				
				"multiArg\tsome strong\t5\ttrue\t3",
				"noArg\t",
				"sample\tsample command one string arg"
		};
		List<Command> parsedCommands = logic.parseCommandFromFile(Arrays.asList(lines));
		assertNotNull(parsedCommands);
		assertEquals(3, parsedCommands.size());
		assertThat(parsedCommands.get(0), instanceOf(MultiArgCommand.class));
		assertThat(parsedCommands.get(1), instanceOf(NoArgCommand.class));
		assertThat(parsedCommands.get(2), instanceOf(SampleCommand.class));
		assertEquals(3, ((MultiArgCommand) parsedCommands.get(0)).getByte());
	}
}
