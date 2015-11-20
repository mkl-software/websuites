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
package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Specifies a browser configuration which will define a browser accessible for tests.
 * <p>
 * The browser can be then simply referred using its local ID, like "ff", "chrome" etc.
 * </p>
 * <p>
 * Sample browser definition:
 * </p>
 * <pre>
 *       browserConfiguration =
 *        {@literal @BrowserConfig}(
 *            id = "chrome"
 *            displayName = "Chrome"
 *            browserType = BrowserType.CHROME
 *            webDriverPath = "src/test/resources/ChromeDriver.exe)
 * </pre>
 * <p>
 * You can use the IDs to identify browsers in:
 * </p>
 * <ul>
 * <li>scenario files in "if statement, e.g.:<code>if  browser=ie</code></li>
 * <li>scenario files using <code>${browser}</code> property</li>
 * <li>in Java test clases using <code>WebSuiteProperties.get().getProperty("browser")</code></li>
 * </ul>
 * <p>
 * You can define more than one browser for a given type, for example if you want to use different
 * FireFox profiles. Just specify unique IDs for each browser and the same <code>BrowserType</code>.
 * </p>
 * 
 * @author Marcin Klosinski
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BrowserConfig {

    /**
     * Browser type (or family). Based on this type the proper WebDriver implementation
     * will be used (like e.g. {@link org.openqa.selenium.firefox.FirefoxDriver}
     */
    enum BrowserType {

        INTERNET_EXPLORER, CHROME, FIREFOX, SAFARI, OPERA, HTML, NONE
    }

    /**
     * Browser family.
     * @see BrowserType
     */
    BrowserType browserType();

    /**
     * Local ID. This ID will be e.g. available as <code>currentBrowser</code> in properties file.
     */
    String id();

    /**
     * Browser display name when rendered in test names. It will help identyfing for which browser
     * the current test is run.
     */
    String displayName();

    /**
     * Absolute or relative path to the actual web driver.
     */
    String webDriverPath() default "";
}
