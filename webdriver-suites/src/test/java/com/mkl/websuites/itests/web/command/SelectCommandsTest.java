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