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
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.check.CheckPageSourceMatchesCommand;


@CommandDescriptor(name = "~checkPageSourceMatches", argumentTypes = String.class)
public class NegCheckPageSourceMatchesCommand extends CheckPageSourceMatchesCommand {

    public NegCheckPageSourceMatchesCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {

        assertThatUrl.overridingErrorMessage(
                "Page source expected NOT to match '%s', but it does for page source: '%s'", pageSource,
                currentPageSource).doesNotMatch(CommandUtils.patternOf(pageSource));
    }

}
