package com.mkl.websuites.itests.web.command;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.TestClass;
import com.mkl.websuites.internal.tests.WebSuiteStandaloneTest;
import com.mkl.websuites.itests.web.BrowsersConfig;
import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;



public class GotoCommandTest extends WebSuitesResultCheck {



    public static class GotoCheckTest extends WebSuiteStandaloneTest {

        @Override
        protected void runWebTest() {
            assertEquals("Goto test local web", browser.getTitle());

        }

        @Override
        protected String getTestName() {
            return "Check goto title";
        }

    }

    @WebSuites(browsers = "${env.testBrowser}",
            scenarios = @ScenarioFile("src/test/resources/integration/command/gotoLocalAddress.scn"),
            tests = @TestClass(GotoCheckTest.class), browserResusableConfiguration = BrowsersConfig.class)
    public static class LocalRunner extends WebSuitesRunner {
    }


    @Test
    public void testGotoLocalWebAddress() throws Throwable {

        Result testResult = super.checkWebTestResult(LocalRunner.class);

        checkRunCount(BASE_RUN_COUNT_FOR_BROWSER_TEST + 1, testResult); // +1 for
                                                                        // LocalUnderlyingGotoTest

        checkIfNoFailures(testResult);
    }


}
