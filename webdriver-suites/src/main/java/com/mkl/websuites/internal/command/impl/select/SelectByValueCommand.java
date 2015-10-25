package com.mkl.websuites.internal.command.impl.select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;

@CommandDescriptor(name = "selectByValue", argumentTypes = {String.class, String.class})
public class SelectByValueCommand extends OperationOnWebElement {

	private static final String VALUE_PARAM = "value";


	public SelectByValueCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	@SuppressWarnings("serial")
	public SelectByValueCommand(final String selector,final String value) {
		super(new HashMap<String, String>() {{
			put("css", selector);
			put(VALUE_PARAM, value);
		}
		});
	}
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		if (!CommandUtils.checkIfElementIsSelect(elem)){
			fail(String.format("Element picked by selector '%s' must be a SELECT, but is '%s'",
					by, elem.getTagName()));
		}
		
		String value = parameterMap.get(VALUE_PARAM);
		
		Select select = new Select(elem);
		
		doSelect(value, select);
	}

	protected void doSelect(String value, Select select) {
		select.selectByValue(value);
	}

	
	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();
		
		for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
			schemaValidationRule.addMandatoryElements(VALUE_PARAM);
		}
		
		return parentValidationRules;
	}
	
}
