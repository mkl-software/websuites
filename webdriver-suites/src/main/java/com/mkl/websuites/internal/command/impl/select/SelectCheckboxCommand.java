package com.mkl.websuites.internal.command.impl.select;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;



@CommandDescriptor(name = "selectCheckbox", argumentTypes = {String.class})
public class SelectCheckboxCommand extends OperationOnWebElement {



	public SelectCheckboxCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	@SuppressWarnings("serial")
	public SelectCheckboxCommand(final String selector) {
		super(new HashMap<String, String>() {{
			put("css", selector);
		}
		});
	}
	
	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		if (!CommandUtils.checkIfElementIsCheckBox(elem)){
			fail(String.format("Element picked by selector '%s' must be an INPUT[type=CHECKBOX], but is '%s'",
					by, elem.getTagName() + "[type=" + elem.getAttribute("type") + "]"));
		}
		
		selectCheckbox(elem);
		
	}

	protected void selectCheckbox(WebElement elem) {
		if (!elem.isSelected()) {
			elem.click();
		}
	}

}
