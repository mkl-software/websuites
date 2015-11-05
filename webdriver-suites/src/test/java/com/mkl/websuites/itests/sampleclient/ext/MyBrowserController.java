package com.mkl.websuites.itests.sampleclient.ext;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.browser.StandardBrowserController;


@Slf4j
public class MyBrowserController extends StandardBrowserController {

    private static MyBrowserController instance = new MyBrowserController();

    public static MyBrowserController getInstance() {
        return instance;
    }


    @Override
    public String getBrowserDisplayName(String currentBrowser) {
        log.debug("custom getDisplayName()");
        return "{custo} " + super.getBrowserDisplayName(currentBrowser);
    }

}
