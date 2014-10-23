package com.mkl.websuites.test.unit.core;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.command.impl.validator.CommandSchemaValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;




public class CommandSchemaValidatorTest {

	
	private CommandSchemaValidator sut;



	@Test
	public void shouldPassValidationWhenNoValidationRules() {
		//given
		sut = new CommandSchemaValidator(SchemaValidationRule.emptyValidationRules());
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("paramOne", "value one");
		someMap.put("paramTwo", "value 2");
		someMap.put("param3", "value 3");
		//when
		sut.validateCommandSchema(someMap);
		sut.validateCommandSchema(new HashMap<String, String>());
		//then no exception expected
		
	}
	
	
	
	@Test
	public void shouldPassValidationOneTopParamNoOthers() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
		//then no exception expected
	}
	
	
	@Test(expected = WebSuitesException.class)
	public void shouldNotPassValidationOneTopParamNoOthers() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("unwanted", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
		//then no exception expected
	}
	
	
	
	@Test
	public void shouldPassValidationOneTopOneRequiredNoOptional() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("additionalRequired");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("additionalRequired", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
		//then no exception expected
	}
	
	
	
	@Test(expected = WebSuitesException.class)
	public void shouldNotPassValidationOneTopOneRequiredNoOptional1() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("notExpectedElem", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
	}
	
	
	@Test(expected = WebSuitesException.class)
	public void shouldNotPassValidationOneTopOneRequiredNoOptional2() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("required", "some value doesn't matter what");
		someMap.put("notExpectedElem", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
	}



	@Test
	public void shouldPassValidationOneRequiredOneOptional1() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required");
		rule.addOptionalElements("optional");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("required", "some value doesn't matter what");
		someMap.put("optional", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
		//then no exception expected
	}
	
	
	@Test
	public void shouldPassValidationOneRequiredOneOptional2() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required");
		rule.addOptionalElements("optional");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("required", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
		//then no exception expected
	}
	
	
	
	@Test(expected = WebSuitesException.class)
	public void shouldNotPassValidationOneRequiredOneOptional1() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required");
		rule.addOptionalElements("optional");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("optional", "some value doesn't matter what");
		//when
		sut.validateCommandSchema(someMap );
	}
	
	
	@Test(expected = WebSuitesException.class)
	public void shouldNotPassValidationOneRequiredOneOptional2() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required");
		rule.addOptionalElements("optional");
		sut = new CommandSchemaValidator(rule);
		// and
		Map<String, String> someMap = new HashMap<String, String>();
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("required", "some value doesn't matter what");
		someMap.put("not optional", "some value doesn't matter what");
		//then
		sut.validateCommandSchema(someMap );
	}
}
