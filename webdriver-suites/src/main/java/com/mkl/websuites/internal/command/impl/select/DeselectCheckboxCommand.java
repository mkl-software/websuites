package com.mkl.websuites.internal.command.impl.select;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;



@CommandDescriptor(name = "deselectCheckbox", argumentTypes = {String.class})
public class DeselectCheckboxCommand extends SelectCheckboxCommand {



    public DeselectCheckboxCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public DeselectCheckboxCommand(final String selector) {
        super(selector);
    }

    @Override
    protected void selectCheckbox(WebElement elem) {
        if (elem.isSelected()) {
            elem.click();
        }
    }

}
