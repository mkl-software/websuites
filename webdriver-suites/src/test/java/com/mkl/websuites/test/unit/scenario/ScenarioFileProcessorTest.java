package com.mkl.websuites.test.unit.scenario;

import static org.junit.Assert.*;
import mockit.Deencapsulation;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mkl.websuites.internal.command.CommandParser;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.test.core.ServiceBasedTest;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScenarioFileProcessorTest extends ServiceBasedTest {

	
	
	@BeforeClass
	public static void init() {
		ServiceFactory.init(null);
	}
	
	
	@Override
	protected Class<?> getServiceUnderTest() {
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
}
