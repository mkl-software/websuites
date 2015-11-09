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

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;



@Slf4j
public class AllCheckCommandsFailingTest extends WebSuitesResultCheck {

    public static final String FAILING_SOFT_COMMANDS_SCENARIO_FILE =
            "src/test/resources/integration/command/check/allCheckCommands_failing.scn";



    @WebSuites(browsers = "${env.testBrowser}", scenarios = @ScenarioFile(FAILING_SOFT_COMMANDS_SCENARIO_FILE),
            browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunnerAllCheckCommandsPassing extends WebSuitesRunner {
    }



    @Test
    public void shouldRunAllCheckCommandsAllVersionsAllSoftFailing() throws Throwable {

        List<String> lines =
                Files.readAllLines(FileSystems.getDefault().getPath(FAILING_SOFT_COMMANDS_SCENARIO_FILE),
                        StandardCharsets.UTF_8);

        int expectedNumberOfSoftChecks = 0;
        for (String string : lines) {
            if (string.trim().startsWith("softCheck") || string.trim().startsWith("~softCheck")) {
                expectedNumberOfSoftChecks++;
            }
        }

        log.debug("number of soft check commands in scenario file: {}", expectedNumberOfSoftChecks);

        Result testResult = super.checkWebTestResult(LocalRunnerAllCheckCommandsPassing.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

        assertThat(testResult.getFailureCount()).isEqualTo(1);

        assertThat(testResult.getFailures().get(0).getMessage().substring(0, 100)) // only begining
                                                                                   // of soft check
                                                                                   // msg
                .contains("The following " + expectedNumberOfSoftChecks + " assertions failed");
    }



}
