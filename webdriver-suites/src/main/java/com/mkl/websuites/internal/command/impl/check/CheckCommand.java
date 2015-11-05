package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;


@CommandDescriptor(name = "check", argumentTypes = String.class)
public class CheckCommand extends OperationOnWebElement {


    public CheckCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckCommand(String elemement) {
        super(elemement);
        super.elementSelector = elemement;
    }

    @Override
    protected void doOperationOnElement(WebElement elem) {

        // no operation, just checking if exists without clicking

    }

}
