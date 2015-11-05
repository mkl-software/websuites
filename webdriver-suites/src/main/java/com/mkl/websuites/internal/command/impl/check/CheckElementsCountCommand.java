package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkElementsCount", argumentTypes = {String.class, Integer.class})
public class CheckElementsCountCommand extends OperationOnWebElement {


    protected String expectedNumberOfElements;

    protected static final String EXPECTED_NUMBER_PARAM = "count";

    public CheckElementsCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckElementsCountCommand(final String selector, final Integer expectedNumberOfElements) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(EXPECTED_NUMBER_PARAM, expectedNumberOfElements + "");
            }
        });
    }


    protected class CheckElementsCount extends AbstractSingleStringCheck {


        @Override
        protected String getStringParam() {
            List<WebElement> elements = CheckElementsCountCommand.this.browser.findElements(by);
            return elements.size() + "";
        }

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage(
                    "Expecting number of elements picked by selector '%s' to be %s but was %s", by,
                    actualNumberOfElements, expectedNumberOfElements).isEqualTo(expectedNumberOfElements);
        }
    }

    @Override
    protected void doOperationOnElement(WebElement elem) {

        expectedNumberOfElements = parameterMap.get(EXPECTED_NUMBER_PARAM);

        defineCheckLogic().runStandardCommand();

    }

    protected AbstractCheck defineCheckLogic() {
        return new CheckElementsCount();
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(EXPECTED_NUMBER_PARAM);
        }

        return parentValidationRules;
    }
}
