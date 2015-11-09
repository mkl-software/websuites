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

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;

@CommandDescriptor(name = "checkPageSource", argumentTypes = String.class)
public class CheckPageSourceCommand extends AbstractSingleStringCheck {

    protected String pageSource;

    public CheckPageSourceCommand(String pageSource) {
        this.pageSource = pageSource;
    }


    @Override
    protected String getStringParam() {
        return browser.getPageSource();
    }

    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {

        assertThatUrl.overridingErrorMessage(
                "Page source expected to be exactly '%s', but the actual page source was '%s'", pageSource,
                currentPageSource).isEqualTo(pageSource);
    }


}
