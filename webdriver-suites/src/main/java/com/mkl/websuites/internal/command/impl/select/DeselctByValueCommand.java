package com.mkl.websuites.internal.command.impl.select;

import java.util.Map;

import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "deselectByValue", argumentTypes = {String.class, String.class})
public class DeselctByValueCommand extends SelectByValueCommand {

    public DeselctByValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public DeselctByValueCommand(String selector, String value) {
        super(selector, value);
    }


    @Override
    protected void doSelect(String value, Select select) {
        select.deselectByValue(value);
    }
}
