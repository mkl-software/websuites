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
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;



@CommandDescriptor(name = "checkCheckboxSelected", argumentTypes = {String.class})
public class CheckCheckboxCommand extends OperationOnWebElement {


    protected String actualCheckedValue;
    private String checkedAttributeValue = "checked";



    public CheckCheckboxCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckCheckboxCommand(final String selector) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
            }
        });
    }



    protected class CheckCheckBox extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting checkbox selected by selector '%s'" + " to be checked (selected)", by)
                    .isEqualToIgnoringCase("true");
        }

        @Override
        protected String getStringParam() {
            return actualCheckedValue;
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        if (!(elem.getTagName().equalsIgnoreCase("input") && elem.getAttribute("type").equalsIgnoreCase("checkbox"))) {
            fail("Element expected to be a checkbox");
        }

        actualCheckedValue = elem.getAttribute(checkedAttributeValue);

        actualCheckedValue = actualCheckedValue == null ? "false" : actualCheckedValue;

        AbstractCheck checkLogic = defineCheckLogic();

        checkLogic.runStandardCommand();


    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckCheckBox();
    }


}
