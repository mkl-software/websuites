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
