package com.mkl.websuites.test.unit.scenario;

import java.util.Arrays;
import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mockit.Deencapsulation;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandPostProcessor;
import com.mkl.websuites.internal.command.impl.EndControlFlowHandler;
import com.mkl.websuites.internal.command.impl.RepeatControlFlowHandler;
import com.mkl.websuites.test.core.ServiceBasedTest;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;



@RunWith(JUnitParamsRunner.class)
public class ControlFlowProcessingTest extends ServiceBasedTest<CommandPostProcessor> {

	@Override
	protected Class<CommandPostProcessor> getServiceUnderTestClass() {
		return CommandPostProcessor.class;
	}
	
	
	@SuppressWarnings("unused")
	private Object[] parametersForTestMaxDepthFindingAlgorithm() {
		return $(
                $(Arrays.asList((Command) new NoArgCommand()), 0),
                $(Arrays.asList(
                		(Command) new NoArgCommand(),
                		new NoArgCommand(),
                		new NoArgCommand()),
        			0),
    			$(Arrays.asList(
                		(Command) new RepeatControlFlowHandler(),
                		new NoArgCommand(),
                		new EndControlFlowHandler()),
        			1),
    			$(Arrays.asList(
    					(Command) new RepeatControlFlowHandler(),
    					new NoArgCommand(),
    					new RepeatControlFlowHandler(),
    					new EndControlFlowHandler(),
    					new EndControlFlowHandler()),
					2),
				$(Arrays.asList(
						(Command) new RepeatControlFlowHandler(),
						new RepeatControlFlowHandler(),
						new RepeatControlFlowHandler(),
						new RepeatControlFlowHandler(),
						new NoArgCommand(),
						new EndControlFlowHandler(),
						new EndControlFlowHandler(),
						new EndControlFlowHandler(),
						new EndControlFlowHandler()),
					4)
           );
	}
	
	
	@Test
	@Parameters
	public void testMaxDepthFindingAlgorithm(List<Command> input, int expectedDepth) {
		
		int depth = Deencapsulation.invoke(logic(), "findMaxNestingDepth", input);
		
		assertEquals(expectedDepth, depth);
	}
	
	
	@Test(expected = WebSuitesException.class)
	public void testWrongControlFlowBlock() {
		
		List<Command> commandsFromScn = Arrays.asList(
				(Command) new RepeatControlFlowHandler(),
				new NoArgCommand(),
				new NoArgCommand(),
				new NoArgCommand()
				);
		
		Deencapsulation.invoke(logic(), "findMaxNestingDepth", commandsFromScn);
	}
	
	
	
	@Test
	public void testNoControlFlowBlock() {
		
		List<Command> commandsFromScn = Arrays.asList(
				(Command) new NoArgCommand(),
				new NoArgCommand(),
				new NoArgCommand(),
				new NoArgCommand()
				);
		
		List<Command> processed = logic().postProcessCommands(commandsFromScn);
		
		assertEquals(4, processed.size());
		
	}
	
	
	@Test
	public void testOneExternalControlFlowBlock() {
		
		List<Command> commandsFromScn = Arrays.asList(
				(Command) new RepeatControlFlowHandler(),
				new NoArgCommand(),
				new NoArgCommand(),
				new NoArgCommand(),
				new NoArgCommand(),
				new EndControlFlowHandler()
				);
		
		List<Command> processed = logic().postProcessCommands(commandsFromScn);
		
		assertEquals(1, processed.size());
		
		Command first = processed.get(0);
		assertTrue(first instanceof RepeatControlFlowHandler);
		
		assertEquals(4, ((RepeatControlFlowHandler) first).getNestedCommands().size());
	}
	
	
	
	@Test
	public void testThreeFirstLevelControlFlowBlocks() {
		
		List<Command> commandsFromScn = Arrays.asList(
				(Command) new RepeatControlFlowHandler(),
				new NoArgCommand(),
				new EndControlFlowHandler(),
				new RepeatControlFlowHandler(),
				new NoArgCommand(),
				new NoArgCommand(),
				new EndControlFlowHandler(),
				new RepeatControlFlowHandler(),
				new NoArgCommand(),
				new NoArgCommand(),
				new NoArgCommand(),
				new EndControlFlowHandler()
				);
		
		List<Command> processed = logic().postProcessCommands(commandsFromScn);
		
		assertEquals(3, processed.size());
		
		Command first = processed.get(0);
		assertTrue(first instanceof RepeatControlFlowHandler);
		assertEquals(1, ((RepeatControlFlowHandler) first).getNestedCommands().size());
		
		Command second = processed.get(1);
		assertTrue(second instanceof RepeatControlFlowHandler);
		assertEquals(2, ((RepeatControlFlowHandler) second).getNestedCommands().size());
		
		Command third = processed.get(2);
		assertTrue(third instanceof RepeatControlFlowHandler);
		assertEquals(3, ((RepeatControlFlowHandler) third).getNestedCommands().size());
	}
	
	
	
	@Test
	public void testThreeLevelNestingWithTwoFirstLevelCommands() {
		
		List<Command> commandsFromScn = Arrays.asList(
				(Command) new NoArgCommand(),
				new RepeatControlFlowHandler(),
				new RepeatControlFlowHandler(),
				new RepeatControlFlowHandler(),
				new NoArgCommand(),
				new NoArgCommand(),
				new NoArgCommand(),
				new EndControlFlowHandler(),
				new EndControlFlowHandler(),
				new EndControlFlowHandler(),
				new NoArgCommand()
				);
		
		List<Command> processed = logic().postProcessCommands(commandsFromScn);
		
		assertEquals(3, processed.size());
		
		assertTrue(processed.get(0) instanceof NoArgCommand);
		assertTrue(processed.get(1) instanceof RepeatControlFlowHandler);
		assertTrue(processed.get(2) instanceof NoArgCommand);
		
		List<Command> secondLevel = ((RepeatControlFlowHandler) processed.get(1)).getNestedCommands();
		assertEquals(1, secondLevel.size());
		
		List<Command> thirdLevel = ((RepeatControlFlowHandler) secondLevel.get(0)).getNestedCommands();
		assertEquals(1, thirdLevel.size());
		
		List<Command> fourthLevel = ((RepeatControlFlowHandler) thirdLevel.get(0)).getNestedCommands();
		assertEquals(3, fourthLevel.size());
		
		for (Command command : fourthLevel) {
			assertTrue(command instanceof NoArgCommand);
		}
	}

}
