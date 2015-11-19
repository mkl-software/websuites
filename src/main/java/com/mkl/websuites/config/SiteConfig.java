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
 * Defines a site configuration which can be used in scenario files or Java tests.
 * <p>
 * <b>Important:</b> this configuration is not mandatory. It's only a convenient way to define
 * global site URL that can be later accessed via properties. However, you can still use host, port
 * and adders explicitly.
 * </p>
 * <p>
 * Nevertheless, a good practice is to define a site URL here and then refer to it using properties.
 * It allows for instance to flexibly define different configuration for development and test
 * environments (just specify two WebSuite runners).
 * </p>
 * <p>
 * Example usage of <code>SiteConfig</code>:
 * </p>
 * <code>
 * siteConfig = {@literal @}SiteConfig(<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;host = dev-myhost.com,<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;port = 8090<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;basePath = /myApp<br/>
 * )
 * </code>
 * <p>
 * Now you can access this configuration in scenario files like this:
 * </p>
 * <code>
 * goto ${site}/index.html<br/>
 * checkTitle  Main page<br/>
 * goto ${site}/search/globalSearch.html<br/>
 * checkLinkTextContaining  search<br/>
 * </code>
 * <p>
 * And in your test classes that extend {@link com.mkl.websuites.MultiBrowserTestCase} you have a
 * String field available <code>site</code>, which can holds concatenated site URL string.
 * </p>
 * <br/>
 * 
 * @author Marcin Klosinski
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SiteConfig {

    /**
     * URL protocol, defaults to "http".
     */
    String protocol() default "http";

    /**
     * Qualified host name or IP address. Defaults to "localhost".
     */
    String host() default "localhost";


    /**
     * Port number. Defaults to 8080.
     * 
     * @return
     */
    int port() default 8080;

    /**
     * Root context path. Defaults to "/".
     * 
     * @return
     */
    String basePath() default "/";

    /**
     * Global wait timeout for WebDriver routines. It's used every time WebDriver implicit wait is
     * invoked, like in WebDriver.findElement(By selector) invocations.
     */
    int waitTimeout() default 10;

    /**
     * If set to <code>true</code>, after all tests are finished the browser(s) will not get closed.
     * <p>
     * It's useful when you need to perform some manual examination of the web content after all
     * tests are done.
     * </p>
     * Defaults to <code>false</code>.
     */
    boolean doNotCloseBrowserAtTheEnd() default false;
}
