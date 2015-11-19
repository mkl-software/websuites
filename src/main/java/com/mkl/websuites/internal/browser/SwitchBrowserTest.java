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
package com.mkl.websuites.internal.browser;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public class SwitchBrowserTest extends TestCase {



    private BrowserController browserController = ServiceFactory.get(BrowserController.class);

    private String browserName;

    private RunnableForBrowser runnableForBrowser;



    public SwitchBrowserTest(String browser, RunnableForBrowser runnableForBrowser) {
        this.browserName = browser;
        this.runnableForBrowser = runnableForBrowser;
    }



    @Override
    public String getName() {

        String displayName = browserController.getBrowserDisplayName(browserName);

        // no browser configured for this ID
        if (displayName == null) {
            displayName = "Not configured!";
        }

        return "Initializing browser: " + displayName;
    }


    @Override
    protected void runTest() throws Throwable {

        runnableForBrowser.runForBrowser(browserName);

        WebDriver driver = browserController.getWebDriver();

        // check if first test, then nothing to close yet, otherwise close:
        if (driver != null) {
            driver.quit();
        }

        String closedBrowser = browserController.removeCurrentBrowser();

        log.debug("removing browser [ " + closedBrowser + "] from list of browsers to run");

        browserController.setNextWebDriver();
    }

}
