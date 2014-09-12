package com.mkl.websuites.test.unit.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import mockit.Deencapsulation;

import org.junit.Test;

import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandBuilder;
import com.mkl.websuites.internal.command.impl.SampleCommand;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.test.core.ServiceBasedTest;



@SuppressWarnings("rawtypes")
public class CommandBuilderTest extends ServiceBasedTest {

	
	@Override
	protected Class<?> getServiceUnderTest() {
		return CommandBuilder.class;
	}
	
	
	
	@Test
	public void _0testSampleCommandArgTypes() {
		
		CommandBuilder logic = ServiceFactory.get(CommandBuilder.class);
		
		Deencapsulation.invoke(
				logic, "scanClasspathForCommands");
		Map commandArgTypes = Deencapsulation.getField(logic, "commandTypesMap");
		
		assertTrue(commandArgTypes.size() > 0);
		
		List types = (List) commandArgTypes.get("sample");
		
		assertNotNull(types);
		
		assertEquals(1, types.size());
		assertEquals(String.class, types.get(0));
	}
	
	
	private List<Object> convertArguments(String[] args, List types) {
		CommandBuilder logic = ServiceFactory.get(CommandBuilder.class);
		List<Object> values = Deencapsulation.invoke(
				logic, "convertArgumentsToProperTypes", args, types);
		return values;
	}



	@Test
	public void testCommandTypeResolution0() {
		
		String[] args = new String[] {};
		List types = Arrays.asList(String.class);
		
		List<Object> values = convertArguments(args, types);
		
		assertNotNull(values);
		assertEquals(values.size(), 0);
	}
	
	@Test
	public void testCommandTypeResolution1() {
		
		String[] args = new String[] {"argument value"};
		List types = Arrays.asList(String.class);
		
		List<Object> values = convertArguments(args, types);
		
		assertNotNull(values);
		assertEquals(values.size(), 1);
		assertEquals("argument value", values.get(0));
	}



	@Test
	public void testCommandTypeResolution2() {
		
		String[] args = new String[] {"3", "6", "12"};
		List types = Arrays.asList(Integer.class, Short.class, Long.class);
		
		List<Object> values = convertArguments(args, types);
		
		assertNotNull(values);
		assertEquals(values.size(), 3);
		assertEquals(3, values.get(0));
		assertEquals((short) 6, values.get(1));
		assertEquals(12L, values.get(2));
	}
	
	
	
	@Test
	public void testCommandTypeResolution3() {
		
		String[] args = new String[] {"true", "-2", "something", "another"};
		List types = Arrays.asList(Boolean.class, Integer.class, String.class, String.class);
		
		List<Object> values = convertArguments(args, types);
		
		assertNotNull(values);
		assertEquals(values.size(), 4);
		assertTrue((boolean) values.get(0));
		assertEquals(-2, values.get(1));
		assertEquals("something", values.get(2));
		assertEquals("another", values.get(3));
	}
	
	
	
	@Test
	public void testSimpleCommand() {
		
		CommandBuilder logic = ServiceFactory.get(CommandBuilder.class);
		
		Command command = Deencapsulation.invoke(
				logic, "instantiateCommand", "sample", new String[] {"command argument"});
		
		assertNotNull(command);
		
		assertTrue(command instanceof SampleCommand);
		
		assertEquals("command argument", Deencapsulation.getField(command, "arg"));
	}

}
