package com.mkl.websuites.internal.command.impl.select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;

@CommandDescriptor(name = "selectByIndex", argumentTypes = {String.class, Integer.class})
public class SelectByIndexCommand extends OperationOnWebElement {

	private static final String INDEX_PARAM = "index";


	public SelectByIndexCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	@SuppressWarnings("serial")
	public SelectByIndexCommand(final String selector,final Integer index) {
		super(new HashMap<String, String>() {{
			put("css", selector);
			put(INDEX_PARAM, index + "");
		}
		});
	}
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		if (!CommandUtils.checkIfElementIsSelect(elem)){
			fail(String.format("Element picked by selector '%s' must be a SELECT, but is '%s'",
					by, elem.getTagName()));
		}
		
		int index = Integer.valueOf(parameterMap.get(INDEX_PARAM));
		
		Select select = new Select(elem);
		
		doSelect(index, select);
	}

	protected void doSelect(int index, Select select) {
		select.selectByIndex(index);
	}

	
	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements(INDEX_PARAM);
		}
		
		return parentValidationRules;
	}
	

	@Override
	protected List<ParameterValueValidator> defineParameterValueValidators() {
		
		List<ParameterValueValidator> parentValidators = super.defineParameterValueValidators();
		
		parentValidators.add(new IntegerNumberParamValidator(INDEX_PARAM));
		
		return parentValidators;
	}
}
