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
package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextContainsCommand;



@CommandDescriptor(name = "~checkElementTextContains", argumentTypes = {String.class, String.class})
public class NegCheckElementTextContainsCommand extends CheckElementTextContainsCommand {

    public NegCheckElementTextContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementTextContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }

    protected class NegCheckElementTextContains extends CheckElementTextContains {


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting inner text of web page element with selector '%s'"
                            + " NOT to contain '%s', but was '%s'", by, expectedText, elementText).doesNotContain(
                    expectedText);
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementTextContains();
    }
}
