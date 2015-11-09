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

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkElementAttrValueContains", argumentTypes = {String.class, String.class, String.class})
public class CheckElementAttributeValueContainsCommand extends CheckElementAttributeValueCommand {

    public CheckElementAttributeValueContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckElementAttributeValueContainsCommand(String selector, String attributeName,
            String expectedAttributeValue) {
        super(selector, attributeName, expectedAttributeValue);
    }


    protected class CheckElementAttrValueContains extends CheckElementAttrValue {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting value of attribute '%s' in the web page element with selector '%s'"
                            + " to contain '%s', but it is '%s'", inputAttributeName, by, expectedAttributeValue,
                    foundElement.getAttribute(inputAttributeName)).contains(expectedAttributeValue);
        }
    }



    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementAttrValueContains();
    }
}
