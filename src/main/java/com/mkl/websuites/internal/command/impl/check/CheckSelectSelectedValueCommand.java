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


@CommandDescriptor(name = "checkSelectedValue", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedValueCommand extends CheckSelectSelectedTextCommand {



    public CheckSelectSelectedValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public CheckSelectSelectedValueCommand(final String selector, final String expectedText) {
        this(new HashMap<String, String>() {
            {
                put("css", selector);
                put("value", expectedText);
            }
        });
        selectedTextParam = "value";
    }


    protected class CheckSelectSelectedValue extends CheckSelectSelectedText {


        protected String getOptionString(WebElement elem) {
            return elem.getAttribute("value");
        }


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting option with id '%s' to be selected  in the SELECT element picked " + "by selector '%s'",
                    expectedSelectText, by).isNotEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedValue();
    }

}
