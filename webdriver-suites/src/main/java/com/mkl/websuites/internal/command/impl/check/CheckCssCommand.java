package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkCss", argumentTypes = {String.class, String.class, String.class})
public class CheckCssCommand extends OperationOnWebElement {


    protected static final String CSS_ATTR_NAME = "cssAttr";
    protected static final String CSS_ATTR_VALUE = "cssValue";

    protected String expectedCssAttributeValue;
    protected String actualCssAttributeValue;
    protected String cssAttributeName;


    public CheckCssCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckCssCommand(final String selector, final String cssAttName, final String expectedCssValue) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(CSS_ATTR_NAME, cssAttName);
                put(CSS_ATTR_VALUE, expectedCssValue);
            }
        });
    }



    protected class CheckCss extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting CSS attribute '%s' on the web page element with selector '%s'"
                            + " to have a value '%s', but was '%s'", cssAttributeName, by, expectedCssAttributeValue,
                    actualCssAttributeValue).isEqualTo(expectedCssAttributeValue);
        }

        @Override
        protected String getStringParam() {
            return actualCssAttributeValue;
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        cssAttributeName = parameterMap.get(CSS_ATTR_NAME);
        expectedCssAttributeValue = parameterMap.get(CSS_ATTR_VALUE);

        actualCssAttributeValue = elem.getCssValue(cssAttributeName);

        AbstractCheck checkLogic = defineCheckLogic();

        checkLogic.runStandardCommand();
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckCss();
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(CSS_ATTR_NAME);
        }
        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(CSS_ATTR_VALUE);
        }

        return parentValidationRules;
    }

}
