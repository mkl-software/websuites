package com.mkl.websuites.internal.command.impl.select;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "selectByTextContaining", argumentTypes = {String.class, String.class})
public class SelectByTextContainingCommand extends SelectByTextCommand {

	public SelectByTextContainingCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SelectByTextContainingCommand(String selector, String text) {
		super(selector, text);
	}

	
	@Override
	protected void doSelect(String text, Select select) {
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			WebElement option = options.get(i);
			if (option.getText().contains(text)) {
				select(select, i);
			}
		}
	}

	protected void select(Select select, int i) {
		select.selectByIndex(i);
	}
}
