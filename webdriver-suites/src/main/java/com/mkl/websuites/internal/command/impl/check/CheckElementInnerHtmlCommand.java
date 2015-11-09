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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "checkElementInnerHTML", argumentTypes = {String.class, String.class})
public class CheckElementInnerHtmlCommand extends OperationOnWebElement {

    private static final String INNER_HTML_PARAM = "innerHTML";

    protected String expectedInnerHTML;

    protected String actualInnerHTML;


    public CheckElementInnerHtmlCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckElementInnerHtmlCommand(final String selector, final String expectedText) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(INNER_HTML_PARAM, expectedText);
            }
        });
    }



    protected class CheckElementInnerHTML extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting inner HTML in the web page element with selector '%s'"
                            + " to be exactly '%s', but was '%s'", by, expectedInnerHTML, elementText).isEqualTo(
                    expectedInnerHTML);
        }

        @Override
        protected String getStringParam() {
            return actualInnerHTML;
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        actualInnerHTML = elem.getAttribute(INNER_HTML_PARAM);

        // not all browsers may support innerHTML attribute on WebElement level, so
        // use Javascript invocation then instead:
        if (actualInnerHTML == null) {

            actualInnerHTML =
                    (String) ((JavascriptExecutor) browser).executeScript("return arguments[0].innerHTML;", elem);
        }

        AbstractCheck checkLogic = defineCheckLogic();

        expectedInnerHTML = parameterMap.get(INNER_HTML_PARAM);

        checkLogic.runStandardCommand();


    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckElementInnerHTML();
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(INNER_HTML_PARAM);
        }

        return parentValidationRules;
    }

}
