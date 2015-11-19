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

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ObjectArrayAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectOptionTextCommand;


@CommandDescriptor(name = "~checkSelectOptionText", argumentTypes = {String.class, String.class})
public class NegCheckSelectOptionTextCommand extends CheckSelectOptionTextCommand {

    public NegCheckSelectOptionTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectOptionTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectOptionText extends CheckSelectOptionText {

        @Override
        protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {

            ((ObjectArrayAssert<?>) assertion)
                    .extracting("text")
                    .overridingErrorMessage(
                            "Expecting SELECT element picked by selector '%s'" + " NOT to have option with text '%s'",
                            by, expectedSelectText).doesNotContain(expectedSelectText);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectOptionText();
    }

}
