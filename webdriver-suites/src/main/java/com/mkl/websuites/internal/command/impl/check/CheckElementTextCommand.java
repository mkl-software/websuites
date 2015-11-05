package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkElementText", argumentTypes = {String.class, String.class})
public class CheckElementTextCommand extends OperationOnWebElement {


    protected String expectedText;

    protected String actualInnerText;


    public CheckElementTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckElementTextCommand(final String selector, final String expectedText) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put("text", expectedText);
            }
        });
    }


    protected class CheckElementText extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion
                    .overridingErrorMessage(
                            "Expecting inner text of web page element with selector '%s'"
                                    + " to be exactly '%s', but was '%s'", by, expectedText, elementText).isEqualTo(
                            expectedText);
        }

        @Override
        protected String getStringParam() {
            return actualInnerText;
        }
    }


    /**
     * Workaround for situation where this class should inherit both from AbstractCheck and
     * OperationOnWebElement.
     * 
     * @param webElement
     * @return
     */
    protected AbstractCheck defineCheckLogic() {

        return new CheckElementText();
    }

    @Override
    protected void doOperationOnElement(WebElement elem) {

        actualInnerText = elem.getText();

        AbstractCheck checkLogic = defineCheckLogic();

        expectedText = parameterMap.get("text");

        checkLogic.runStandardCommand();


    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements("text");
        }

        return parentValidationRules;
    }

}
