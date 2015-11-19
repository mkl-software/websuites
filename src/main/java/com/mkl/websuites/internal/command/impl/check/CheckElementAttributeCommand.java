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
