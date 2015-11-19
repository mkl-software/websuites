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


@CommandDescriptor(name = "checkCssClass", argumentTypes = {String.class, String.class})
public class CheckCssClassCommand extends OperationOnWebElement {


    protected static final String CSS_CLASS_ATTR = "class";

    protected String actualCssClassNames;
    protected String expectedCssClassName;


    public CheckCssClassCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckCssClassCommand(final String selector, final String cssClassName) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(CSS_CLASS_ATTR, cssClassName);
            }
        });
    }



    protected class CheckCssClass extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting web page element with selector '%s'"
                            + " to have a CSS class '%s', but it has classes '%s'", by, expectedCssClassName,
                    actualCssClassNames).contains(expectedCssClassName);
        }

        @Override
        protected String getStringParam() {
            return actualCssClassNames;
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        expectedCssClassName = parameterMap.get(CSS_CLASS_ATTR);

        actualCssClassNames = elem.getAttribute("class");

        AbstractCheck checkLogic = defineCheckLogic();

        checkLogic.runStandardCommand();
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckCssClass();
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(CSS_CLASS_ATTR);
        }

        return parentValidationRules;
    }

}
