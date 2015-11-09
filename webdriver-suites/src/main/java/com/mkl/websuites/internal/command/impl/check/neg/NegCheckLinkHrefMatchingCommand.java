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

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefMatchingCommand;


@CommandDescriptor(name = "~checkLinkHrefMatching", argumentTypes = String.class)
public class NegCheckLinkHrefMatchingCommand extends CheckLinkHrefMatchingCommand {

    public NegCheckLinkHrefMatchingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        String actualHref = actualElement != null ? actualElement.getAttribute("href") : "";
        String actualLinkText = actualElement != null ? actualElement.getText() : "";

        assertion.overridingErrorMessage(
                "Expecting link with HREF attribute matching regexp '%s'"
                        + " NOT to exist, but found href='%s' in the link with text '%s'", expectedLinkText,
                actualHref, actualLinkText).isNull();
    }
}
