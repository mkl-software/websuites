package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkElementAttr", argumentTypes = {String.class, String.class})
public class CheckElementAttributeCommand extends OperationOnWebElement {


    protected static final String ATT_NAME_PARAM = "attName";

    protected String inputAttributeName;

    protected String actualAttributeValue;


    public CheckElementAttributeCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckElementAttributeCommand(final String selector, final String expectedText) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(ATT_NAME_PARAM, expectedText);
            }
        });
    }



    protected class CheckElementAttr extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting attribute '%s' in the web page element with selector '%s'" + " to have a value",
                    inputAttributeName, by).isNotEmpty();
        }

        @Override
        protected String getStringParam() {
            return actualAttributeValue;
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        inputAttributeName = parameterMap.get(ATT_NAME_PARAM);
        actualAttributeValue = elem.getAttribute(inputAttributeName);

        AbstractCheck checkLogic = defineCheckLogic();

        checkLogic.runStandardCommand();


    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckElementAttr();
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(ATT_NAME_PARAM);
        }

        return parentValidationRules;
    }

}
