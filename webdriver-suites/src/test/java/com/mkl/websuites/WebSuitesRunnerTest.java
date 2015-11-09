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
package com.mkl.websuites;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;


public class WebSuitesRunnerTest extends WebSuitesResultCheck {


    public static class DefaultWebSuitesNoConfig extends WebSuitesRunner {
    }

    @Test
    public void shouldRunDefaultRunnerNoAnnotation() throws Throwable {
        // when
        Result testResult = super.checkWebTestResult(DefaultWebSuitesNoConfig.class);
        checkIfNoFailures(testResult);
        // then
        int numberOfRunForSetupAndTearDownOnly = 2;
        assertThat(testResult.getRunCount()).isEqualTo(numberOfRunForSetupAndTearDownOnly);
    }



    @WebSuites(browsers = "html")
    public static class HtmlUnitBrowserEmptyTestSuite extends WebSuitesRunner {
    }

    @Test
    public void shouldRunOneHtmlUnitBrowserEmptyTestSuite() throws Throwable {
        // when
        Result testResult = super.checkWebTestResult(HtmlUnitBrowserEmptyTestSuite.class);
        checkIfNoFailures(testResult);
        // then
        int numberOfRunForSetupTearDownAndOneBrowserSetUpTearDown = 2 + 2;
        assertThat(testResult.getRunCount()).isEqualTo(numberOfRunForSetupTearDownAndOneBrowserSetUpTearDown);
    }



    @WebSuites(browsers = {"html", "html", "html"})
    public static class ThreeHtmlUnitBrowsersEmptyTestSuite extends WebSuitesRunner {
    }

    @Test
    public void shouldRunThreeHtmlUnitBrowsersEmptyTestSuite() throws Throwable {
        // when
        Result testResult = super.checkWebTestResult(ThreeHtmlUnitBrowsersEmptyTestSuite.class);
        checkIfNoFailures(testResult);
        // then
        int numberOfRunForSetupTearDownAndThreeBrowsersSetUpTearDown = 2 + (3 * 2);
        assertThat(testResult.getRunCount()).isEqualTo(numberOfRunForSetupTearDownAndThreeBrowsersSetUpTearDown);
    }
}
