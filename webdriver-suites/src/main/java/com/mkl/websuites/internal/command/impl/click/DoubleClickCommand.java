package com.mkl.websuites.internal.command.impl.click;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "doubleClick", argumentTypes = String.class)
public class DoubleClickCommand extends ClickCommand {

    public DoubleClickCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public DoubleClickCommand(String elemement) {
        super(elemement);
    }
    
    
    @Override
    protected void doOperationOnElement(WebElement elem) {
        Actions actions = new Actions(browser);
        actions.doubleClick(elem).perform();
    }    
}
