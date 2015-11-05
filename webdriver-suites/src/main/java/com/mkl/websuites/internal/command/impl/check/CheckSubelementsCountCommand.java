package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkSubelementsCount", argumentTypes = {String.class, String.class, Integer.class})
public class CheckSubelementsCountCommand extends OperationOnWebElement {


    private static final String SUBELEMENTS_PARAM = "subElementsCss";

    protected String expectedNumberOfElements;

    protected By subelementsSelector;

    protected static final String EXPECTED_NUMBER_PARAM = "count";


    public CheckSubelementsCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckSubelementsCountCommand(final String selector, final String subElementSelector,
            final Integer expectedNumberOfElements) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(SUBELEMENTS_PARAM, subElementSelector + "");
                put(EXPECTED_NUMBER_PARAM, expectedNumberOfElements + "");
            }
        });
    }


    protected class CheckSubelementsCount extends AbstractSingleStringCheck {


        @Override
        protected String getStringParam() {
            // find under root element:
            List<WebElement> elements =
                    CheckSubelementsCountCommand.this.foundElement.findElements(subelementsSelector);
            return elements.size() + "";
        }

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage(
                    "Expecting number of elements picked by selector '%s' under element "
                            + "picked by selector '%s' to be %s but was %s", subelementsSelector, by,
                    expectedNumberOfElements, actualNumberOfElements).isEqualTo(expectedNumberOfElements);
        }
    }

    @Override
    protected void doOperationOnElement(WebElement elem) {

        expectedNumberOfElements = parameterMap.get(EXPECTED_NUMBER_PARAM);

        subelementsSelector = By.cssSelector(parameterMap.get(SUBELEMENTS_PARAM));

        defineCheckLogic().runStandardCommand();

    }

    protected AbstractCheck defineCheckLogic() {
        return new CheckSubelementsCount();
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(EXPECTED_NUMBER_PARAM);
            schemaValidationRule.addMandatoryElements(SUBELEMENTS_PARAM);
        }

        return parentValidationRules;
    }
}
