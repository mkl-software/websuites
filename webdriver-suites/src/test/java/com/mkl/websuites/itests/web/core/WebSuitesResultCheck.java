package com.mkl.websuites.itests.web.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import mockit.Deencapsulation;

import org.junit.Before;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.internal.services.ServiceFactory;

public abstract class WebSuitesResultCheck extends JettyBasedTest {


    public static final int BASE_RUN_COUNT_FOR_BROWSER_TEST = 5;

    public static final int BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST = 2;


    protected Result checkWebTestResult(Class<? extends WebSuitesRunner> localRunner) throws Throwable {

        Result result = new JUnitCore().run(new InternalWebSuitesRunner(localRunner));

        return result;

    }

    protected void checkRunCount(int expectedRunCount, Result result) {
        assertEquals(expectedRunCount, result.getRunCount());
    }

    protected void checkIfNoFailures(Result result) {
        if (result.getFailureCount() > 0) {

            System.out.println(result.getFailures());

            StringBuffer sb = new StringBuffer();

            for (Failure failure : result.getFailures()) {
                sb.append("[" + failure.getTestHeader() + "] ").append(failure.getMessage());

            }

            fail("There are failurs in the unerlying test being invoked, see " + "details below:\n" + sb.toString());
        }
    }



    @Before
    public void prepareTest() {

        Deencapsulation.setField(ServiceFactory.class, "isInitialized", false);
    }



}
