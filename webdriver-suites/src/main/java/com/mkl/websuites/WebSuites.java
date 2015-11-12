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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.config.BrowserConifg;
import com.mkl.websuites.config.Extension;
import com.mkl.websuites.config.Folder;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.SiteConfig;
import com.mkl.websuites.config.TestClass;

/**
 * Configuration for runner classes that extends {@link com.mkl.websuites.WebSuitesRunner}.
 * <p>
 * This annotation allows to define test suites and browsers to run these tests against.
 * </p>
 * <p>
 * Sample configuration might look like:
 * </p>
 * <code>
 * {@literal @}WebSuites(<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;browsers = {"chrome", "ff", "ie"},<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;tests = {{@literal @}TestClass({MyTestClass1.class, MyTestClass2,class})<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;scenarios = {{@literal @}ScenarioFile(resources/tests/MyWebTestScenario.scn)<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;browserConfiguration = {<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @BrowserConfig}(<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;id = "chrome",<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;displayName = "Chrome",<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;browserType = BrowserType.CHROME<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;webDriverPath = "src/test/resources/ChromeDriver.exe"<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;),<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{@literal @BrowserConfig}(<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;id = "ie",<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;displayName = "Internet Explorer",<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;browserType = BrowserType.INTERNET_EXPLORER<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;webDriverPath = "src/test/resources/IEDriver.exe"<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)}<br/>
 * )<br/>
 * public class MyRunner extends WebSuitesRunner {} <br/><br/>
 * </code>
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface WebSuites {

    /**
     * List of browsers to run all tests suites against. By default it is an empty list.
     * <p>
     * The elements of this list are string identifiers of browsers, like "<b>ff</b>" or
     * "<b>chrome</b>".
     * </p>
     * that come from {@link com.mkl.websuites.config.BrowserConfig} annotation.
     * <p>
     * By default three identifiers are available:
     * </p>
     * <ul>
     * <li><b>ff</b> for default FireFox driver</li>
     * <li><b>html</b> for HtmlUnitDriver</li>
     * <li><b>none</b> for non-browser tests</li>
     * </ul>
     * <p>
     * For custom browser definitions which use explicit web drivers please specify a corresponding
     * element in the <code>browserConfiguration</code> element
     * </p>
     */
    String[] browsers() default {};


    /**
     * Use this attribute to define folder path from which all scenario files will be run.
     * <p>
     * You can specify more than one paths.
     * </p>
     * <p>
     * Each folder is specified using {@link com.mkl.websuites.config.Folder} annotation.
     * </p>
     * <p>
     * Example usage:
     * </p>
     * <code>
     * folders = {@literal @}Folder(path = "/tests/allSearchTests")
     * </code>
     */
    Folder[] folders() default {};


    /**
     * Use this attribute to specify explicitly which scenario files to run.
     * <p>
     * You can specify more than one files.
     * </p>
     * <p>
     * Each folder is specified using {@link com.mkl.websuites.config.ScenarioFile} annotation.
     * </p>
     * <p>
     * Example usage:
     * </p>
     * <code>
     * scenarios = {{@literal @}ScenarioFile("/tests/navigation/menu1.scn"),
     * {@literal @}ScenarioFile("/tests/navigation/menu2.scn"),
     * {@literal @}ScenarioFile("/tests/navigation/menu3.scn")}
     * </code>
     */
    ScenarioFile[] scenarios() default {};


    /**
     * Use this attribute to specify explicitly which test classes to run.
     * <p>
     * You can specify more than one test class.
     * </p>
     * <p>
     * Each test class is specified using {@link com.mkl.websuites.config.TestClass} annotation.
     * </p>
     * <p>
     * Example usage:
     * </p>
     * <code>
     * tests = {{@literal @}TestClass(DetailedSavedSearchesTest.class),
     * {@literal @}TestClass(LoginFailureTest.class)}
     * </code>
     */
    TestClass[] tests() default {};

    String propertiesFileName() default "";


    /**
     * You can specify main site configuration here. In the
     * {@link com.mkl.websuites.config.SiteConfig} annotation you can specify server host, port and
     * base address. Then, in your tests you can access this information using properties:
     * <ul>
     * <li><code>${site}</code> - concatenated protocol, host, port nad base path</li>
     * <li><code>${site.protocol}</code> - protocol</li>
     * <li><code>${site.host}</code> - host name or IP address</li>
     * <li><code>${site.port}</code> - port number</li>
     * <li><code>${site.basePath}</code> - base context path</li>
     * </ul>
     * <p>
     * The most commonly used setting is <code>waitTimeout</code>, which sets the global timeout for
     * all implicit WebDriver waits.
     * </p>
     */
    SiteConfig site() default @SiteConfig;

    /**
     * Specifies browsers that can be used in multi-browser tests.
     * <p>
     * Each browser is specified using {@link com.mkl.websuites.config.BrowserConfig} annotation,
     * which determines browser local ID, dipslay name and web driver physical location.
     * </p>
     * By default the FireFox browser is available so if installedo on the OS it will work
     * out-of-the-box.
     * <p>
     * <p>
     * Sample configuration of custom browser:
     * </p>
     * <code>{@literal @}BrowserConifg(browserType = BrowserType.CHROME, id = "chrome", 
     * displayName = "Firefox", webDriverPath = "src/test/resources/drivers/ChromeDriver.exe"),</code>
     * 
     * @see com.mkl.websuites.config.SiteConfig
     */
    BrowserConifg[] browserConfiguration() default {};


    /**
     * If there are multiple WebSuites runner classes, you can share browser configuration between
     * them and avoid specifying them in each runner separately. Use this class to point to a class
     * which holds {@link com.mkl.websuites.config.BrowserDefinition} annotation.
     * <p>
     * If both <code>browserConfiguration</code> and <code>browserResusableConfiguration</code> are
     * specified, then the browser configuration is merged with a priority to
     * <code>browserConfiguration</code> explicit definitions.
     * </p>
     * 
     * @return
     */
    Class<?> browserResusableConfiguration() default Object.class;


    /**
     * Specifies extensions and customization of the framework.
     * <ol>
     * <li>package paths for custom commands</li>
     * <li>service overrides to deeply modify framework behaviour</li>
     * </ol>
     */
    Extension extension() default @Extension;

}
