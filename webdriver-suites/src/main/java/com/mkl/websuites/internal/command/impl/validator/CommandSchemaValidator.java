package com.mkl.websuites.internal.command.impl.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesException;



public class CommandSchemaValidator {

	
	private List<SchemaValidationRule> schemaValidationRules;
	
	private List<ParameterValueValidator> parameterValueValidators;

	public CommandSchemaValidator(List<SchemaValidationRule> schemaValidationRules,
			List<ParameterValueValidator> paramValueValidators) {
		
		this.schemaValidationRules = schemaValidationRules;
		this.parameterValueValidators = paramValueValidators;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CommandSchemaValidator(SchemaValidationRule ... rules) {
		this(Arrays.asList(rules), (List) new ArrayList<ParameterValueValidator>());
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
		
		for (ParameterValueValidator validator : parameterValueValidators) {
			
			String paramValue = parameters.get(validator.getParamName());
			if (paramValue != null) {
				validator.validateParam(paramValue);
			}
		}
	}

	
	
	public CommandSchemaValidator addParameterValueValidator(ParameterValueValidator validator) {
		parameterValueValidators.add(validator);
		return this;
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
