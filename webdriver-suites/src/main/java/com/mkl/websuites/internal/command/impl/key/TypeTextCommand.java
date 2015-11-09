package com.mkl.websuites.internal.command.impl.key;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;


@CommandDescriptor(name = "type", argumentTypes = {String.class, String.class})
public class TypeTextCommand extends OperationOnWebElement {


    private String textToType;


    public TypeTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    public TypeTextCommand(String element, String textToType) {
        super(element);
        super.elementSelector = element;
        this.textToType = textToType;
    }


    @Override
    protected void doOperationOnElement(WebElement elem) {
        String text = populateStringWithProperties(textToType);
        elem.sendKeys(text);

    }



}
