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


@CommandDescriptor(name = "checkHidden", argumentTypes = {String.class})
public class CheckHiddenCommand extends CheckVisibleCommand {



    public CheckHiddenCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    public CheckHiddenCommand(String selector) {
        super(selector);
    }


    protected class CheckHidden extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String displayValue) {

            assertion.overridingErrorMessage(
                    "Expecting web page element with selector '%s'"
                            + " to be have property 'display: none;', but it was '%s'", by, displayValue).isEqualTo(
                    "none");
        }

        @Override
        protected String getStringParam() {
            return foundElement.getCssValue("display");
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckHidden();
    }


}
