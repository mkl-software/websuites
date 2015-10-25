package com.mkl.websuites.internal.command.impl.select;

import java.util.Map;

import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "deselectByTextContaining", argumentTypes = {String.class, String.class})
public class DeselectByTextContainingCommand extends SelectByTextContainingCommand {

	public DeselectByTextContainingCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public DeselectByTextContainingCommand(String selector, String text) {
		super(selector, text);
	}
	
	@Override
	protected void select(Select select, int i) {
		select.deselectByIndex(i);
	}

}
