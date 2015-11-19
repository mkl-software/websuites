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
