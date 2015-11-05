package com.mkl.websuites.internal.command.impl.select;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;

@CommandDescriptor(name = "deselectAll", argumentTypes = {String.class})
public class DeselectAllCommand extends OperationOnWebElement {



    public DeselectAllCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public DeselectAllCommand(final String selector) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
            }
        });
    }


    @Override
    protected void doOperationOnElement(WebElement elem) {

        if (!CommandUtils.checkIfElementIsSelect(elem)) {
            fail(String.format("Element picked by selector '%s' must be a SELECT, but is '%s'", by, elem.getTagName()));
        }

        Select select = new Select(elem);

        select.deselectAll();
    }


}
