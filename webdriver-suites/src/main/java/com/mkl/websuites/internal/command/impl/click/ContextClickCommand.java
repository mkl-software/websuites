package com.mkl.websuites.internal.command.impl.click;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "contextClick", argumentTypes = String.class)
public class ContextClickCommand extends ClickCommand {

    public ContextClickCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public ContextClickCommand(String elemement) {
        super(elemement);
    }
    
    
    @Override
    protected void doOperationOnElement(WebElement elem) {
        Actions actions = new Actions(browser);
        actions.contextClick(elem).perform();
    }    
}
