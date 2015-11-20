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
package com.mkl.websuites;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.config.BrowserConfig;
import com.mkl.websuites.config.SiteConfig;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.CommonUtils;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;


/**
 * Base class for web tests to be run by WebSuiteRunner.
 * <p>
 * Just extend this class and implement <code>runWebTest</code> method to define a test.
 * </p>
 * <p>
 * Inside the test you have a WebDriver <code>browser</code> field and a bunch of useful convenience
 * methods to create your web logic.</p>
 * 
 * @author Marcin Klosinski
 *
 */
@Slf4j
public abstract class MultiBrowserTestCase extends TestCase {


    /**
     * Global configuration.
     * 
     * @see WebSuites
     */
    protected WebSuites configuration = WebSuitesConfig.get();

    /**
     * Currently running browser. The ID corresponds to the ones defined in
     * <code>WebSuites.browserConfiguration</code>.
     * 
     * @see BrowserConfig
     */
    protected String currentBrowserId;

    /**
     * WebDriver object to operate on the web browser.
     */
    protected WebDriver browser;

    /**
     * Concatenated URL from <code>WebSuites.site</code> configuration.
     * 
     * @see SiteConfig
     */
    protected String site;

    private BrowserController browserController = ServiceFactory.get(BrowserController.class);


    public MultiBrowserTestCase() {
        super();
        SiteConfig siteConfig = configuration.site();

        this.site =
                CommonUtils.normalizeUrlPath(siteConfig.protocol(), siteConfig.host(), siteConfig.port(),
                        siteConfig.basePath());

        this.currentBrowserId = browserController.getLocalBrowserNameForTestInit();
    }



    @Override
    public final String getName() {
        return getTestName() + " [" + currentBrowserId + "]";
    }


    @Override
    protected final void runTest() throws Throwable {

        log.debug("running: " + this.getClass().getName() + " with test name: " + getName());

        this.browser = browserController.getWebDriver();

        runWebTest();
    }


    /**
     * Define web test logic here.
     */
    protected abstract void runWebTest();


    /**
     * Override to provide a custom test name.
     * 
     * @return custom test name
     */
    protected String getTestName() {
        return this.getClass().getName();
    }

}
