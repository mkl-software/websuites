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
import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectOptionTextContaining", argumentTypes = {String.class, String.class})
public class CheckSelectOptionTextContainingCommand extends CheckSelectOptionTextCommand {

    public CheckSelectOptionTextContainingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckSelectOptionTextContainingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class CheckSelectOptionTextContaining extends AbstractSingleStringCheck {


        @Override
        protected String getStringParam() {
            for (WebElement elem : selectOptions) {
                String text = elem.getText();
                if (text != null && predicate(text)) {
                    return text;
                }
            }
            return "";
        }

        protected boolean predicate(String text) {
            return text.contains(expectedSelectText);
        }



        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting SELECT element picked by selector '%s'"
                            + " to have text containing '%s' in one of its options", by, expectedSelectText)
                    .isNotEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectOptionTextContaining();
    }
}
