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

import org.junit.Test;
import org.junit.runner.Result;
import org.openqa.selenium.By;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.TestClass;
import com.mkl.websuites.internal.tests.WebSuiteStandaloneTest;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;



public class RepeatTimesCommandTest extends WebSuitesResultCheck {


    public static class RepeatCheckTest extends WebSuiteStandaloneTest {

        @Override
        protected void runWebTest() {

            String spanText = browser.findElement(By.id("calculationResult")).getText();
            assertEquals("5", spanText);
            String propTestInputTest = browser.findElement(By.id("propTestElement")).getAttribute("value");
            assertEquals("12345", propTestInputTest);
        }

        @Override
        protected String getTestName() {
            return "Repeat n times";
        }

    }



    @WebSuites(browsers = "${env.testBrowser}",
            scenarios = @ScenarioFile("src/test/resources/integration/command/repeatTimes.scn"),
            tests = @TestClass(RepeatCheckTest.class), browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunner extends WebSuitesRunner {
    }



    @Test
    public void testSimpleRepeat() throws Throwable {

        Result testResult = super.checkWebTestResult(LocalRunner.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1, testResult); // + 1 for repeat result
                                                                        // check

        checkIfNoFailures(testResult);

    }



}
