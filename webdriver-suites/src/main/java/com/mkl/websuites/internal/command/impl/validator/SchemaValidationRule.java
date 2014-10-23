package com.mkl.websuites.internal.command.impl.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class SchemaValidationRule {

	
	private @Getter String topLevelRequiredElement;
	
	private @Getter List<String> mandatoryElements;
	
	private @Getter List<String> optionalElements;
	
	private @Getter @Setter String miniDocumentation;

	
	
	public static List<SchemaValidationRule> emptyValidationRules() {
		return Collections.emptyList();
	}
	
	
	public SchemaValidationRule(String topLevelRequiredElement) {
		super();
		this.topLevelRequiredElement = topLevelRequiredElement;
		mandatoryElements = new ArrayList<String>();
		optionalElements = new ArrayList<String>();
	}
	
	
	public SchemaValidationRule addMandatoryElements(String ... elements) {
		mandatoryElements.addAll(Arrays.asList(elements));
		return this;
	}
	
	
	public SchemaValidationRule addOptionalElements(String ... elements) {
		optionalElements.addAll(Arrays.asList(elements));
		return this;
	}
	
	
	@Override
	public String toString() {
		
		if (miniDocumentation != null && !miniDocumentation.isEmpty()) {
			
			return miniDocumentation;
		}
		
		return String.format("\nparameter \"%s\" with mandatory params %s "
				+ "and optional params %s",
				topLevelRequiredElement,
				mandatoryElements.toString(),
				optionalElements.toString());
	}
}
