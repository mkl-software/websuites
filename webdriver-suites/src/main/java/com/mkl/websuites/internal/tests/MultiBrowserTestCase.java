package com.mkl.websuites.internal.tests;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public abstract class MultiBrowserTestCase extends TestCase {


    private BrowserController browserController = ServiceFactory.get(BrowserController.class);

    protected WebSuites configuration = WebSuitesConfig.get();

    protected String currentBrowser;

    protected WebDriver browser;

    protected String basePath;



    public MultiBrowserTestCase() {
        super();
        this.basePath =
                normalizeUrlPath(configuration.site().host(), configuration.site().port(), configuration.site()
                        .basePath());
        this.currentBrowser = browserController.getLocalBrowserNameForTestInit();
    }


    private String normalizeUrlPath(String host, int portNumber, String basePath) {

        host = host.matches("[a-z]+:///?.*") ? host : "http://" + host;
        String port = portNumber == 80 ? "" : ":" + portNumber;
        String path = basePath;
        path = path.startsWith("/") || path.isEmpty() ? path : "/" + path;
        String url = host + port + path;
        // normalize "/" but after http:// section:
        url = url.substring(0, 7) + url.substring(7).replaceAll("//", "/");
        return url;
    }


    @Override
    public String getName() {
        return getTestName() + " [" + currentBrowser + "]";
    }


    @Override
    protected void runTest() throws Throwable {

        log.debug("running: " + this.getClass().getName() + " with test name: " + getName());

        this.browser = browserController.getWebDriver();

        runLocally();
    }

    protected abstract void runLocally();


    protected abstract String getTestName();

}
