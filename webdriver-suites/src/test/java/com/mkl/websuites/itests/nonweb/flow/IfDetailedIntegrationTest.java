package com.mkl.websuites.itests.nonweb.flow;

import static com.mkl.websuites.itests.web.core.WebSuitesResultCheck.BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST;
import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.browser.StandardBrowserController;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.itests.web.core.CommandInvocationVerifier;
import com.mkl.websuites.itests.web.core.TestUtils;

public class IfDetailedIntegrationTest {


    private static final String SCN_DIR = "src/test/resources/integration/non-web/if/";
    private final CommandInvocationVerifier commandVerifier = CommandInvocationVerifier.getInstance();



    @WebSuites(scenarios = @com.mkl.websuites.config.ScenarioFile(""), browsers = "none")
    public static class Runner extends WebSuitesRunner {
    }


    @Test
    public void shouldRunForSimpleBrowserCheck() throws Throwable {

        // given
        overrideScenarioFileNameAnnotation(SCN_DIR + "01.scn");
        mockCurrentBrowser("ff");
        commandVerifier.clearVerificationQueue();

        commandVerifier.expectInvocations("this is firefox");

        // when
        Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));

        // then
        TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 1);
        commandVerifier.checkRemaining();
    }



    @Test
    public void shouldRunForComplexBrowserCheck() throws Throwable {

        // given
        overrideScenarioFileNameAnnotation(SCN_DIR + "02.scn");
        mockCurrentBrowser("ie");
        commandVerifier.clearVerificationQueue();

        commandVerifier.expectInvocations("this is ie", "not chrome", "not firefox");

        // when
        Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));

        // then
        TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 1);
        commandVerifier.checkRemaining();
    }


    @Test
    public void shouldRunForNestedBrowserCheck() throws Throwable {

        // given
        overrideScenarioFileNameAnnotation(SCN_DIR + "03.scn");
        mockCurrentBrowser("ie");
        commandVerifier.clearVerificationQueue();

        commandVerifier.expectInvocations("this is firefox or ie", "this is not firefox and not ie",
                "not ff, not chrome, not safari");

        // when
        Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));

        // then
        TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 1);
        commandVerifier.checkRemaining();
    }



    @Test
    public void shouldRunForPropertyCondition() throws Throwable {

        // given
        overrideScenarioFileNameAnnotation(SCN_DIR + "04.scn");
        commandVerifier.clearVerificationQueue();

        commandVerifier.expectInvocations("correct1", "correct2", "correct3", "correct4");

        // and
        WebSuitesUserProperties.get().clear();

        // when
        Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));

        // then
        TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 1);
        commandVerifier.checkRemaining();
    }



    private void mockCurrentBrowser(final String browser) {
        new MockUp<StandardBrowserController>() {
            @Mock
            String currentBrowser() {
                return browser;
            }
        };
    }


    private void overrideScenarioFileNameAnnotation(final String scenarioName) throws Throwable {

        TestUtils.prepareMockScenarioFileName(scenarioName);
    }



}
