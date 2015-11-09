package com.mkl.websuites.internal.command.impl.select;

import java.util.Map;

import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "deselectByText", argumentTypes = {String.class, String.class})
public class DeselectByTextCommand extends SelectByTextCommand {

    public DeselectByTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public DeselectByTextCommand(String selector, String text) {
        super(selector, text);
    }


    @Override
    protected void doSelect(String text, Select select) {
        select.deselectByVisibleText(text);
    }
}
