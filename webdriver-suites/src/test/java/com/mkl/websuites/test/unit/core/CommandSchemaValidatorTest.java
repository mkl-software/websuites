package com.mkl.websuites.test.unit.core;

import java.util.HashMap;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.command.impl.validator.CommandSchemaValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;




@RunWith(JUnitParamsRunner.class)
public class CommandSchemaValidatorTest {

	
	private CommandSchemaValidator sut;


	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();

	
	
	
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
	
	
	
	@Test
	public void shouldNotPassValidationOneTopOneRequiredNoOptional1() {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required");
		sut = new CommandSchemaValidator(rule);
		Map<String, String> someMap = new HashMap<String, String>();
		// and
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("notExpectedElem", "some value doesn't matter what");
		//then
		expectValidationException("[required]", "[]");
		sut.validateCommandSchema(someMap );
	}
	
	
	@Test
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
		//then
		expectValidationException("[required]", "[]");
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
	
	
	
	@Test
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
		//then
		expectValidationException("[required]", "[optional]");
		sut.validateCommandSchema(someMap );
	}
	
	
	@Test
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
		expectValidationException("[required]", "[optional]");
		sut.validateCommandSchema(someMap );
	}
	
	
	
	
	@Parameters({"", "optional1;optional2;optional3", "optional1", "optional2", "optional3",
		         "optional3;optional1", "optional2;optional3", "optional1;optional3"})
	@Test
	public void shouldPassValidationManyRequired(String optionals) {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required1");
		rule.addMandatoryElements("required2");
		rule.addMandatoryElements("required3");
		rule.addOptionalElements("optional1");
		rule.addOptionalElements("optional2");
		rule.addOptionalElements("optional3");
		sut = new CommandSchemaValidator(rule);
		// and
		Map<String, String> someMap = new HashMap<String, String>();
		someMap.put("topElem", "some value doesn't matter what");
		someMap.put("required1", "some value doesn't matter what");
		someMap.put("required2", "some value doesn't matter what");
		someMap.put("required3", "some value doesn't matter what");
		String[] options = optionals.split(";");
		for (String optional : options) {
			if (!optional.isEmpty()) {
				someMap.put(optional, "some value doesn't matter what");
			}
		}
		//then
		sut.validateCommandSchema(someMap );
	}
	
	
	
		@Parameters({"required1", "required2", "required3",
	        "required3;required1", "required2;required3", "required1;required3"})
		@Test
		public void shouldNotPassValidationManyRequired(String required) {
		//given
		SchemaValidationRule rule = new SchemaValidationRule("topElem");
		rule.addMandatoryElements("required1");
		rule.addMandatoryElements("required2");
		rule.addMandatoryElements("required3");
		sut = new CommandSchemaValidator(rule);
		// and
		Map<String, String> someMap = new HashMap<String, String>();
		someMap.put("topElem", "some value doesn't matter what");
		String[] requiredParams = required.split(";");
		for (String param : requiredParams) {
			if (!param.isEmpty()) {
				someMap.put(param, "some value doesn't matter what");
			}
		}
		//then
		expectValidationException("[required1, required2, required3]", "[]");
		sut.validateCommandSchema(someMap );
		}



	private void expectValidationException(String requiredParams, String optionalParams) {
		
		expectedException.expect(WebSuitesException.class)
			.hasMessageStartingWith("Given parameters")
			.hasMessageContaining("don't match command allowed parameters")
			.hasMessageContaining(requiredParams)
			.hasMessageContaining(optionalParams);
	}
}
