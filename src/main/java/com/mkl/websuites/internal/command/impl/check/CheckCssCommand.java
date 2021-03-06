/**
 * Copyright 2015 MKL Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;
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
