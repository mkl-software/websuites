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
import com.mkl.websuites.internal.command.impl.check.CheckElementSiblingCountCommand;


@CommandDescriptor(name = "~checkElementSiblingCount", argumentTypes = {String.class, Integer.class})
public class NegCheckElementSiblingCountCommand extends CheckElementSiblingCountCommand {

    public NegCheckElementSiblingCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementSiblingCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected class NegCheckSiblingCount extends CheckSiblingCount {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage(
                    "Expecting number of siblings of element picked by " + "selector '%s' NOT to be %s", by,
                    actualNumberOfElements).isNotEqualTo(expectedNumberOfElements);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSiblingCount();
    }
}
