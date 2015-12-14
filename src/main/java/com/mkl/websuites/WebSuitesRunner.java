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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.assertj.core.util.VisibleForTesting;
import org.junit.runner.RunWith;

import com.mkl.websuites.config.Folder;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.TestClass;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.CommonUtils;
import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.browser.RunnableForBrowser;
import com.mkl.websuites.internal.browser.SwitchBrowserTest;
import com.mkl.websuites.internal.browser.TearDownBrowserTest;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.internal.tests.ScenarioFileTest;
import com.mkl.websuites.internal.tests.ScenarioFolderTest;
import com.mkl.websuites.internal.tests.SetUpAllTest;
import com.mkl.websuites.internal.tests.TearDownAllTest;



/**
 * Extend this class to create a WebSuites runner and run your sophisticated test suites against
 * multiple browsers.
 * <p>
 * To define test suites as well as configuration for tests, use {@link com.mkl.websuites.WebSuites}
 * annotation.
 * </p>
 * <p>
 * To specify custom behavior for test preparation and tear-down, you can override following
 * methods:
 * </p>
 * <ul>
 * <li><code>setUp()</code></li>
 * <li><code>tearDown()</code></li>
 * <li><code>setUpBeforeBrowser(String browserId)</code></li>
 * <li><code>tearDownAfterBrowser(String browserId)</code></li>
 * </ul>
 * 
 * @author Marcin Klosinski
 *
 */
@Slf4j
@RunWith(InternalWebSuitesRunner.class)
public class WebSuitesRunner {


    @WebSuites
    public static class DefaultConfiguration extends WebSuitesRunner {
    }


    private static String currentlyDefiningBrowser;

    @VisibleForTesting
    public WebSuitesRunner() {}

    public WebSuitesRunner(Class<? extends WebSuitesRunner> runningClass) {

        Class<? extends WebSuitesRunner> configClass;

        if (runningClass.isAnnotationPresent(WebSuites.class)) {
            configClass = runningClass;
        } else {
            configClass = DefaultConfiguration.class;

        }
        WebSuitesConfig.initializeWebsuitesConfig(configClass);

        ServiceFactory.init();
    }


    /**
     * Runs before all tests are started. Can be used to prepare a global test environment.
     */
    protected void setUp() {
        log.debug("set up all tests (default impl");
    }

    /**
     * Runs after all tests for all browsers are finished and browsers are shut down.
     */
    protected void tearDown() {
        log.debug("tear down all tests (default impl)");
    }

    /**
     * Runs before all tests for given browser are started.
     * 
     * @param currentBrowser browser ID that is about to be opened
     */
    protected void setUpBeforeBrowser(String currentBrowser) {
        log.debug("set up before browser '{}' (default impl)", currentBrowser);
    }

    /**
     * Runs after all tests for given browser are finished.
     * 
     * @param currentBrowser browser ID that is about to be opened
     */
    protected void tearDownAfterBrowser(String currentBrowser) {
        log.debug("tear down after browser '{}' (default impl)", currentBrowser);
    }


    /**
     * Override to provide custom name for master suite.
     * 
     * @return  name of the master suite
     */
    protected String getMasterSuiteName() {
        return "Multi-browser test suite";
    }



    /**
     * Used internally by the custom JUnit runner to define master test suite.
     * 
     */
    public final TestSuite defineMasterSuite() throws InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

        log.debug("master suite method");

        WebSuites config = WebSuitesConfig.get();

        TestSuite suite = new TestSuite();
        suite.setName(getMasterSuiteName());


        ServiceFactory.get(BrowserController.class).initializeBrowsersEnvironment(config);

        String[] browsers = clobberPropertiesInBrowserIds(config);

        addSetUpSuite(suite);


        // check if run for non-browser mode:
        if (browsers.length == 1 && browsers[0].equals(CommonUtils.NO_BROWSER_ID)) {
            configureSingleNonBrowserSuite(suite, buildAllTests(config));
        }

        for (String browser : browsers) {

            if (browser.equals(CommonUtils.NO_BROWSER_ID)) {
                log.warn("No-browser found among browser tests - skipping no-browser. Please use a config"
                        + " with only ONE browser set to 'none' if you want to run tests without any browser");
                continue;
            }

            TestSuite browserSuite = buildBrowserSuite(browser);

            List<Test> allTestsToRun = buildAllTests(config);

            addTestsToBrowserSuite(allTestsToRun, browserSuite);

            suite.addTest(browserSuite);

            addTearDownForBrowser(browserSuite, browser);

        }

        addTearDownSuite(suite);

        return suite;
    }



    private String[] clobberPropertiesInBrowserIds(WebSuites config) {
        String[] origBrowserIds = config.browsers();
        String[] processedIds = new String[origBrowserIds.length];
        for (int i = 0; i < origBrowserIds.length; i++) {
            String id = origBrowserIds[i];
            id = CommonUtils.populateStringWithProperties(id);
            if (id.startsWith("$")) {
                //id = "chrome"; // uncomment for dev
                throw new WebSuitesException(String.format("Please specify property for browser to run '%s', "
                        + "for example: -D%s=chrome", id, id));
            }
            processedIds[i] = id;
        }
        return processedIds;
    }



    private List<Test> buildAllTests(WebSuites config) {

        // TODO: implement the order from ordinal= parameters.

        List<Test> result = new ArrayList<>();

        ScenarioFile[] scenarios = config.scenarios();
        for (int i = 0; i < scenarios.length; i++) {
            ScenarioFile scenarioFile = scenarios[i];
            String filePath = scenarioFile.value();
            result.add(new ScenarioFileTest(filePath));
        }

        Folder[] folders = config.folders();
        for (int i = 0; i < folders.length; i++) {
            Folder folder = folders[i];
            result.add(new ScenarioFolderTest(folder.path(), folder.ignoreSubfolders(), folder.sortingStrategy()));
        }

        TestClass[] testClasses = config.tests();
        for (int i = 0; i < testClasses.length; i++) {
            TestClass testDefinition = testClasses[i];
            Class<? extends Test> test = testDefinition.value();
            try {
                result.add(test.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new WebSuitesException("Cannot create an instance of test class " + test, e);
            }
        }

        return result;
    }

    private void addTearDownForBrowser(TestSuite browserSuite, String browser) {

        browserSuite.addTest(new TearDownBrowserTest(browser, new RunnableForBrowser() {

            @Override
            public void runForBrowser(String browserId) {
                tearDownAfterBrowser(browserId);
            }
        }));
    }



    private void configureSingleNonBrowserSuite(TestSuite suite, List<Test> tests) {
        setCurrentlyDefiningBrowser(CommonUtils.NO_BROWSER_ID); // TODO: move it to a TestContext
        TestSuite browserSuite = new TestSuite("Running without any browser");
        addTestsToBrowserSuite(tests, browserSuite);
        suite.addTest(browserSuite);
    }



    private TestSuite buildBrowserSuite(String browser) {

        setCurrentlyDefiningBrowser(browser);

        ServiceFactory.get(BrowserController.class).addBrowser(browser);

        TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");

        browserSuite.addTest(new SwitchBrowserTest(browser, new RunnableForBrowser() {

            @Override
            public void runForBrowser(String browserId) {
                setUpBeforeBrowser(browserId);
            }
        }));
        return browserSuite;
    }



    private void addTearDownSuite(TestSuite suite) {
        suite.addTest(new TearDownAllTest(new Runnable() {

            @Override
            public void run() {

                tearDown();
            }
        }));
    }

    private void addSetUpSuite(TestSuite suite) {
        suite.addTest(new SetUpAllTest(new Runnable() {

            @Override
            public void run() {

                setUp();
            }
        }));
    }

    private void addTestsToBrowserSuite(List<Test> tests, TestSuite browserSuite) {

        for (Test test : tests) {

            // Test dynamicTest = testClass.newInstance();
            browserSuite.addTest(test);
        }
    }



    /**
     * To allow quickly identification in the code for which browser are tests currently defined
     * for.
     * <p>Used internally by the framework.</p>
     * 
     * @return  ID of the browser the tests are currently defined for.
     */
    public static String getCurrentlyDefiningBrowser() {
        return currentlyDefiningBrowser;
    }

    private static synchronized void setCurrentlyDefiningBrowser(String currentlyDefiningBrowser) {
        WebSuitesRunner.currentlyDefiningBrowser = currentlyDefiningBrowser;
    }
}
