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
import com.mkl.websuites.internal.command.impl.check.CheckUrlCommand;


@CommandDescriptor(name = "~checkUrl", argumentTypes = String.class)
public class NegCheckUrlCommand extends CheckUrlCommand {

    public NegCheckUrlCommand(String url) {
        super(url);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {

        assertThatUrl.overridingErrorMessage("Page URL expected NOT to be '%s'", expectedUrl).isNotEqualTo(expectedUrl);
    }

}
