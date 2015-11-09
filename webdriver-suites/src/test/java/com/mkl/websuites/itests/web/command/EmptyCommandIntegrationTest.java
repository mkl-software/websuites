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
import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.itests.cmd.SampleCommand;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;



public class EmptyCommandIntegrationTest extends WebSuitesResultCheck {

    @WebSuites(scenarios = @ScenarioFile("src/test/resources/unit/scenarios/running/oneSampleCommand.scn"),
            browsers = "html")
    public static class LocalRunner extends WebSuitesRunner {
    }



    @Test
    public void verifySampleCommandInvocation() throws Throwable {

        // not a perfect verification, would pass also if SampleCommand constructor wasn't
        // invoked at all... But in this when argument string is modified, the test doesn't pass
        // it's OK for testing purposes
        new MockUp<SampleCommand>() {
            @Mock
            public void $init(String argument) {
                assertThat(argument).isEqualTo("from scenario file");
            }
        };

        Result testResult = super.checkWebTestResult(LocalRunner.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST, testResult);

    }



}
