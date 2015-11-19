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
package com.mkl.websuites.itests.web.command;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;


public class CheckCommandTest extends WebSuitesResultCheck {


    @WebSuites(browsers = "${env.testBrowser}",
            scenarios = @ScenarioFile("src/test/resources/integration/command/check1.scn"),
            browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunnerExistingElemCssId extends WebSuitesRunner {
    }

    @WebSuites(browsers = "${env.testBrowser}",
            scenarios = @ScenarioFile("src/test/resources/integration/command/check2.scn"),
            browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunnerNotExistingElemCssId extends WebSuitesRunner {
    }

    @WebSuites(browsers = "${env.testBrowser}",
            scenarios = @ScenarioFile("src/test/resources/integration/command/check3.scn"),
            browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunnerForIdParam extends WebSuitesRunner {
    }

    @WebSuites(browsers = "${env.testBrowser}",
            scenarios = @ScenarioFile("src/test/resources/integration/command/check4.scn"),
            browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunnerForXpathParam extends WebSuitesRunner {
    }



    @Test
    public void testElementExistingWithCssId() throws Throwable {

        Result testResult = super.checkWebTestResult(LocalRunnerExistingElemCssId.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

        checkIfNoFailures(testResult);
    }



    @Test
    public void testElementNotExistingWithCssId() throws Throwable {

        Result testResult = super.checkWebTestResult(LocalRunnerNotExistingElemCssId.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

        Assert.assertEquals(1, testResult.getFailureCount());

        Failure failure = testResult.getFailures().get(0);

        String text = failure.getMessage();

        Assert.assertThat("Inproper failure message : " + text, text, CoreMatchers.containsString("not_existing"));
    }



    @Test
    public void testCheckForIdParam() throws Throwable {

        Result testResult = super.checkWebTestResult(LocalRunnerForIdParam.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

        Assert.assertEquals(1, testResult.getFailureCount()); // exactly 1, firtst test OK

        Failure failure = testResult.getFailures().get(0);

        String text = failure.getMessage();

        Assert.assertThat("Inproper failure message : " + text, text, CoreMatchers.containsString("not_existing_id"));
    }



    @Test
    public void testCheckForXpathParamNoFailures() throws Throwable {

        Result testResult = super.checkWebTestResult(LocalRunnerForXpathParam.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

        checkIfNoFailures(testResult);

    }



}
