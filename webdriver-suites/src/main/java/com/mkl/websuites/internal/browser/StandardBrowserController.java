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

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.config.BrowserConifg;
import com.mkl.websuites.config.BrowsersDefinition;
import com.mkl.websuites.internal.WebSuitesException;



@Slf4j
public class StandardBrowserController implements BrowserController {



    protected Queue<String> browsersToRun = new LinkedBlockingQueue<String>();

    protected WebDriver webDriver;


    private int globalTimeout;

    private boolean firstBrowser = true;

    private String localBrowserNameForTestInit;

    private Map<String, Class<?>> driverClassMap = new HashMap<String, Class<?>>();

    private Map<String, String> browserDisplayNameMap = new HashMap<String, String>();



    protected StandardBrowserController() {}

    private static BrowserController instance = new StandardBrowserController();

    public static BrowserController getInstance() {
        return instance;
    }

    @BrowsersDefinition
    private static class DefaultBrowserConfig {
    }


    @Override
    public void initializeBrowsersEnvironment(WebSuites config) {

        globalTimeout = config.site().waitTimeout();

        // populate default browsers config:
        BrowsersDefinition defaultDefinition = DefaultBrowserConfig.class.getAnnotation(BrowsersDefinition.class);

        BrowserConifg[] defaultBrowsers = defaultDefinition.browsers();

        BrowserConifg[] reusableBrowsers = new BrowserConifg[] {};

        if (config.browserResusableConfiguration().isAnnotationPresent(BrowsersDefinition.class)) {
            reusableBrowsers =
                    config.browserResusableConfiguration().getAnnotation(BrowsersDefinition.class).browsers();
        }

        BrowserConifg[] userBrowsers = config.browserConfiguration();

        /*
         * Browser configuration order (each next overwrites previous one):
         * 1. Default configuration
         * 2. Configuration from browserReusableConifugration class
         * 3. Explicit configuration on browserConfiguration annotation
         */

        BrowserConifg[] browsers = ArrayUtils.addAll(defaultBrowsers, reusableBrowsers);
        browsers = ArrayUtils.addAll(browsers, userBrowsers);

        // if specified explicitly, the config will get overwritten in this loop:

        for (BrowserConifg browserConfig : browsers) {

            browserDisplayNameMap.put(browserConfig.id(), browserConfig.displayName());

            switch (browserConfig.browserType()) {
                case CHROME:
                    configureBrowser("webdriver.chrome.driver", browserConfig, ChromeDriver.class);
                    break;
                case FIREFOX:
                    configureBrowser("", browserConfig, FirefoxDriver.class);
                    break;
                case INTERNET_EXPLORER:
                    configureBrowser("webdriver.ie.driver", browserConfig, InternetExplorerDriver.class);
                    break;
                case HTML:
                    configureBrowser("", browserConfig, HtmlUnitDriver.class);
                    break;
                case OPERA:
                    // TODO: implement it
                    break;
                case SAFARI:
                    // TODO: implement it
                    break;
                default:
                    break;

            }

        }

    }



    protected void configureBrowser(String envKey, BrowserConifg browser, Class<?> driverClass) {

        if (!envKey.isEmpty()) {
            System.setProperty(envKey, browser.webDriverPath());
        }
        driverClassMap.put(browser.id(), driverClass);
    }



    @Override
    public void addBrowser(String browser) {
        if (!browsersToRun.offer(browser)) {
            // won't happen, but findbugs complains to check it...
            throw new WebSuitesException("Cannot add browser to the queue, please rerun the test");
        }
        localBrowserNameForTestInit = browser;
    }


    @Override
    public String currentBrowser() {
        return browsersToRun.peek();
    }


    @Override
    public String removeCurrentBrowser() {
        String current = "";
        if (firstBrowser) {
            firstBrowser = false;
        } else {
            current = browsersToRun.poll();
        }
        return current;
    }



    @Override
    public WebDriver getWebDriver() {
        return webDriver;
    }


    @Override
    public void setNextWebDriver() {

        String currentBrowser = currentBrowser();

        WebSuitesUserProperties.get().setProperty("currentBrowser", currentBrowser);
        
        if (!driverClassMap.containsKey(currentBrowser)) {

            log.error("no browser configured for ID: " + currentBrowser);
            throw new WebSuitesException("No browser configured for the ID: '" + currentBrowser
                    + "', Please correct your BrowserConfiguration element and fill information for " + "this browser.");
        }

        Class<?> driverClass = driverClassMap.get(currentBrowser);

        try {
            webDriver = (WebDriver) driverClass.newInstance();
            webDriver.manage().timeouts().implicitlyWait(globalTimeout, TimeUnit.SECONDS);

            if (webDriver instanceof HtmlUnitDriver) {
                ((HtmlUnitDriver) webDriver).setJavascriptEnabled(true);
            }

        } catch (InstantiationException | IllegalAccessException e) {

            String msg =
                    "cannot create an instance of Web Driver for [" + currentBrowser + "] with class: " + driverClass;
            log.error(msg);
            throw new WebSuitesException(msg, e);
        }
    }


    @Override
    public String getLocalBrowserNameForTestInit() {
        return localBrowserNameForTestInit;
    }


    @Override
    public String getBrowserDisplayName(String currentBrowser) {

        return browserDisplayNameMap.get(currentBrowser);
    }

}
