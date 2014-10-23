package com.mkl.websuites.internal.command.impl.validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesException;



public class CommandSchemaValidator {

	
	private List<SchemaValidationRule> schemaValidationRules;

	public CommandSchemaValidator(List<SchemaValidationRule> schemaValidationRules) {
		
		this.schemaValidationRules = schemaValidationRules;
	}
	
	public CommandSchemaValidator(SchemaValidationRule ... rules) {
		this(Arrays.asList(rules));
	}
	
	
	
	
	public void validateCommandSchema(Map<String, String> parameters) {
		
		if (schemaValidationRules.size() == 0) {
			return;
		}
		
		boolean matchAtLeastOneRule = false;
		
		for (SchemaValidationRule rule : schemaValidationRules) {
			
			if (matchRule(parameters, rule)) {
				matchAtLeastOneRule = true;
				break;
			}
			
		}
		
		if (!matchAtLeastOneRule) {
			
			throw new WebSuitesException("Given parameters " + parameters +" don't match command "
					+ "allowed parameters. Please specifiy proper parameters following command "
					+ "documentation. Allowed parameter configurations are: " + schemaValidationRules);
		}
	}


	private boolean matchRule(Map<String, String> parameters,
			SchemaValidationRule rule) {
		
		if (!parameters.keySet().contains(rule.getTopLevelRequiredElement())) {
			
			return false;
		}
		
		if (!checkMandatoryElements(rule, parameters)) {
			return false;
		}
		
		Map<String, String> fileteredForOptional = new HashMap<String, String>(parameters);
		fileteredForOptional.remove(rule.getTopLevelRequiredElement());
		for (String mandatory : rule.getMandatoryElements()) {
			fileteredForOptional.remove(mandatory);
		}
		
		if (!checkIfOnlyOptionalRemains(rule, fileteredForOptional)) {
			return false;
		}
		
		return true;
	}


	
	
	private boolean checkIfOnlyOptionalRemains(SchemaValidationRule rule, Map<String, String> parameters) {
		
		List<String> optionalElements = rule.getOptionalElements();
		for (String key : parameters.keySet()) {
			if (!optionalElements.contains(key)) {
				return false;
			}
		}
		return true;
	}

	
	
	
	private boolean checkMandatoryElements(SchemaValidationRule rule, Map<String, String> parameters) {
		
		List<String> mandatory = rule.getMandatoryElements();
		int counter = 0;
		int expectedCount = mandatory.size();
		for (String elem : mandatory) {
			if (parameters.containsKey(elem)) {
				counter++;
			}
		}
		return counter == expectedCount;
	}
	
	
	
}
