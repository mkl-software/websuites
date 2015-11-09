package com.mkl.websuites.internal.command.impl.select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;

@CommandDescriptor(name = "selectByText", argumentTypes = {String.class, String.class})
public class SelectByTextCommand extends OperationOnWebElement {

    private static final String TEXT_PARAM = "text";


    public SelectByTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public SelectByTextCommand(final String selector, final String text) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(TEXT_PARAM, text);
            }
        });
    }

    @Override
    protected void doOperationOnElement(WebElement elem) {

        if (!CommandUtils.checkIfElementIsSelect(elem)) {
            fail(String.format("Element picked by selector '%s' must be a SELECT, but is '%s'", by, elem.getTagName()));
        }

        String text = parameterMap.get(TEXT_PARAM);

        Select select = new Select(elem);

        doSelect(text, select);
    }

    protected void doSelect(String text, Select select) {
        select.selectByVisibleText(text);
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(TEXT_PARAM);
        }

        return parentValidationRules;
    }

}
