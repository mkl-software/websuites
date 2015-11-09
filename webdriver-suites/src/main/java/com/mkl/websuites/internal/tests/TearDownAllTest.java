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
package com.mkl.websuites.internal.tests;

import junit.framework.TestCase;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;

public class TearDownAllTest extends TestCase {

    private Runnable tearDownLogic;


    public TearDownAllTest(Runnable tearDownLogic) {
        this.tearDownLogic = tearDownLogic;
    }

    @Override
    public String getName() {
        return "Finalize all tests";
    }


    @Override
    protected void runTest() throws Throwable {

        WebSuites config = WebSuitesConfig.get();

        String currentBrowser = WebSuitesRunner.getCurrentlyDefiningBrowser();

        if (!config.site().doNotCloseBrowserAtTheEnd() && currentBrowser != null && !"none".equals(currentBrowser)) {

            ServiceFactory.get(BrowserController.class).getWebDriver().quit();
        }

        tearDownLogic.run();
    }

}
