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

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;



public class SelectCommandsTest extends WebSuitesResultCheck {



    @WebSuites(browsers = "${env.testBrowser}",
            scenarios = @ScenarioFile("src/test/resources/integration/command/select/selectCommands.scn"),
            browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunnerSelectCommands extends WebSuitesRunner {
    }



    @Test
    public void shouldRunSelectCommandsAllPassing() throws Throwable {

        Result testResult = super.checkWebTestResult(LocalRunnerSelectCommands.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

        checkIfNoFailures(testResult);
    }



}
