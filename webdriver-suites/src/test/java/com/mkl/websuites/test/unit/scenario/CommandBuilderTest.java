package com.mkl.websuites.test.unit.scenario;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import mockit.Deencapsulation;

import org.junit.Test;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandBuilder;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.test.core.ServiceBasedTest;



@SuppressWarnings("rawtypes")
public class CommandBuilderTest extends ServiceBasedTest {

	
	@Override
	protected Class<?> getServiceUnderTest() {
		return CommandBuilder.class;
	}
	
	
	
	private List<Object> convertArguments(String[] args, List types) {
		CommandBuilder logic = getLogic();
		List<Object> values = Deencapsulation.invoke(
				logic, "convertArgumentsToProperTypes", args, types);
		return values;
	}



	private CommandBuilder getLogic() {
		CommandBuilder logic = ServiceFactory.get(CommandBuilder.class);
		return logic;
	}



	@Test
	public void testSampleCommandArgTypes() {
		
		CommandBuilder logic = getLogic();
		
		Deencapsulation.invoke(
				logic, "scanClasspathForCommands");
		Map commandArgTypes = Deencapsulation.getField(logic, "commandTypesMap");
		
		assertTrue(commandArgTypes.size() > 0);
		
		List types = (List) commandArgTypes.get("sample");
		
		assertNotNull(types);
		
		assertEquals(1, types.size());
		assertEquals(String.class, types.get(0));
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
		
		CommandBuilder logic = getLogic();
		
		Command command = logic.instantiateCommand("sample", new String[] {"command argument"});
		
		assertNotNull(command);
		
		assertTrue(command instanceof SampleCommand);
		
		assertEquals("command argument", Deencapsulation.getField(command, "arg"));
	}
	
	
	
	@Test
	public void testNoArgCommand() {
		
		CommandBuilder logic = getLogic();
		
		Command command = logic.instantiateCommand("noArg",	new String[] {});
		
		assertNotNull(command);
		
		assertTrue(command instanceof NoArgCommand);
	}



	@Test
	public void testMultiArgCommand() {
		
		CommandBuilder logic = getLogic();
		
		Command command = logic.instantiateCommand("multiArg",
				new String[] {"string value", "5687", "true", "23"});
		
		assertNotNull(command);
		
		assertTrue(command instanceof MultiArgCommand);
		
		assertEquals("string value", Deencapsulation.getField(command, "string"));
		assertEquals(5687, Deencapsulation.getField(command, "integer"));
		assertEquals(true, Deencapsulation.getField(command, "bool"));
		assertEquals((byte) 23, Deencapsulation.getField(command, "bytee"));
	}
	
	
	
	@Test(expected = WebSuitesException.class)
	public void testArgumentErrorWrongArgumentCount() {
		CommandBuilder logic = getLogic();
		logic.instantiateCommand("sample simple value", // without tabs
				new String[] {});
		fail("command should not be craeted");
	}
	
	
	
	@Test(expected = WebSuitesException.class)
	public void testArgumentErrorToManyArguments() {
		CommandBuilder logic = getLogic();
		logic.instantiateCommand("sample",
				new String[] {"param", "another not expected"});
		fail("command should not be craeted");
	}



	@Test
	public void testParameterizedCommandMapParsing1() {
		CommandBuilder logic = getLogic();
		String[] valueLine = new String [] {"param=value", "param2=value2", "param3=value3"};
		List result = convertParams(logic, valueLine);
		assertNotNull(result);
		assertEquals(1, result.size());
		Map paramMap = (Map) result.get(0);
		assertEquals(3, paramMap.size());
		assertEquals("value", paramMap.get("param"));
		assertEquals("value2", paramMap.get("param2"));
		assertEquals("value3", paramMap.get("param3"));
	}



	private List convertParams(CommandBuilder logic, String[] valueLine) {
		return Deencapsulation
				.invoke(logic, "convertArgumentsToParameterMap", (Object) valueLine);
	}
	
	
	@Test
	public void testParameterizedCommandMapParsing2() {
		CommandBuilder logic = getLogic();
		String[] valueLine = new String [] {"param=value anotherThing space",
				"param2=value2=next=next=next=next=end", "param3"};
		List result = convertParams(logic, valueLine);
		assertNotNull(result);
		assertEquals(1, result.size());
		Map paramMap = (Map) result.get(0);
		assertEquals(3, paramMap.size());
		assertEquals("value anotherThing space", paramMap.get("param"));
		assertEquals("value2=next=next=next=next=end", paramMap.get("param2"));
		assertEquals("", paramMap.get("param3"));
	}
}
