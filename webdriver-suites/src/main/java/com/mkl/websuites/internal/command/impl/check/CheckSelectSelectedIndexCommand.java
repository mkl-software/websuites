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
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;


@CommandDescriptor(name = "checkSelectedIndex", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedIndexCommand extends CheckSelectSelectedValueCommand {


    public CheckSelectSelectedIndexCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public CheckSelectSelectedIndexCommand(final String selector, final String expectedText) {
        this(new HashMap<String, String>() {
            {
                put("css", selector);
                put("index", expectedText);
            }
        });
        selectedTextParam = "index";
    }

    protected class CheckSelectSelectedIndex extends CheckSelectSelectedValue {


        protected String getOptionString(WebElement elem) {
            return elem.getAttribute("index");
        }


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion
                    .overridingErrorMessage(
                            "Expecting option at index '%s' to be selected  in the SELECT element picked "
                                    + "by selector '%s'", expectedSelectText, by).isNotEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedIndex();
    }



    @Override
    protected List<ParameterValueValidator> defineParameterValueValidators() {

        List<ParameterValueValidator> parentValidators = super.defineParameterValueValidators();

        parentValidators.add(new IntegerNumberParamValidator("index"));

        return parentValidators;
    }
}
