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
package com.mkl.websuites.itests.web.regression;

import org.junit.Assert;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.SiteConfig;
import com.mkl.websuites.internal.tests.WebSuiteStandaloneTest;
import com.mkl.websuites.itests.web.BrowsersConfig;

public class TestIEPort80 extends WebSuiteStandaloneTest {


    @WebSuites(browsers = {"ie"}, browserResusableConfiguration = BrowsersConfig.class, site = @SiteConfig(
            host = "google.com"))
    public static class LocalRunner extends WebSuitesRunner {
    }



    @Override
    protected void runWebTest() {

        // test default behaior - if no http:// prefix then IE doesn regoznize the URL
        goTo("google.com:80");
        Assert.assertFalse(browser.getTitle().contains("Google"));


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // test if basePath propagation is fixing this automatically:
        System.out.println(site);
        goTo(site);
        Assert.assertTrue(browser.getTitle().contains("Google"));
    }

    @Override
    protected String getTestName() {
        return "IE explicit port 80 on remote sites";
    }

}
