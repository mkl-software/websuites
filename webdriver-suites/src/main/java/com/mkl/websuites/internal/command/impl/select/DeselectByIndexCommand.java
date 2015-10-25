package com.mkl.websuites.internal.command.impl.select;

import java.util.Map;

import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "deselectByIndex", argumentTypes = {String.class, Integer.class})
public class DeselectByIndexCommand extends SelectByIndexCommand {

	public DeselectByIndexCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public DeselectByIndexCommand(String selector, Integer index) {
		super(selector, index);
	}

	
	@Override
	protected void doSelect(int index, Select select) {
		select.deselectByIndex(index);
	}
}
