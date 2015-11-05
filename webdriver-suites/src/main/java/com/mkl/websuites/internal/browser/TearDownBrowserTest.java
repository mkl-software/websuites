package com.mkl.websuites.internal.browser;

import junit.framework.TestCase;

import com.mkl.websuites.internal.services.ServiceFactory;


public class TearDownBrowserTest extends TestCase {



    private BrowserController browserController = ServiceFactory.get(BrowserController.class);

    private String browserName;

    private RunnableForBrowser runnableForBrowser;



    public TearDownBrowserTest(String browser, RunnableForBrowser runnableForBrowser) {
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

        return "Finalizing tests for browser: " + displayName;
    }


    @Override
    protected void runTest() throws Throwable {

        runnableForBrowser.runForBrowser(browserName);
    }

}
