package com.mkl.websuites.test.unit.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.impl.SetPropCommand;
import com.mkl.websuites.internal.services.ServiceFactory;

import static org.junit.Assert.*;


public class PropertiesTest {


	@BeforeClass
	public static void init() {
		ServiceFactory.init(null);
	}
	
	
	@Test
	public void testPopulatePropertiesCommand() {
		
		Map<String, String> props = new HashMap<String, String>();
		
		props.put("one", "valueOne");
		props.put("two", "valueThree");
		props.put("three", "valueThree");
		
		Command populatePropsCommand = new SetPropCommand(props);
		
		populatePropsCommand.run();
		
		for (String key : props.keySet()) {
			assertEquals(props.get(key), WebSuitesUserProperties.get().getProperty(key));
		}
	}
	
	
	
	@Test
	public void testLoadAndConversions() throws FileNotFoundException {
		WebSuitesUserProperties props = WebSuitesUserProperties.get();
		props.clear();
		props.load(new FileInputStream("src/test/resources/unit/props/props1.properties"));
		assertEquals("marcin", props.getProperty("name"));
		assertEquals("klosinski", props.getProperty("lastName"));
		assertEquals(30, props.getNumberProperty("age"));
		assertEquals(true, props.getBooleanProperty("active"));
		assertEquals(false, props.getBooleanProperty("banned"));
	}
	
	
	
	@Test
	public void testLoadSetUnset() throws FileNotFoundException {
		WebSuitesUserProperties props = WebSuitesUserProperties.get();
		props.clear();
		props.load(new FileInputStream("src/test/resources/unit/props/props1.properties"));
		props.unset("lastName");
		props.unset("banned");
		assertTrue(props.isSet("name"));
		assertFalse(props.isSet("lastName"));
		assertTrue(props.isSet("age"));
		assertTrue(props.isSet("active"));
		assertFalse(props.isSet("banned"));
	}
}
