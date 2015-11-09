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
import com.mkl.websuites.internal.command.impl.check.CheckElementTextCommand;


@CommandDescriptor(name = "~checkElementText", argumentTypes = {String.class, String.class})
public class NegCheckElementTextCommand extends CheckElementTextCommand {

    public NegCheckElementTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckElement extends CheckElementText {


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion
                    .overridingErrorMessage(
                            "Expecting inner text of web page element with selector '%s'" + " NOT to be '%s'", by,
                            expectedText).isNotEqualTo(expectedText);
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {

        return new NegCheckElement();
    }

}
