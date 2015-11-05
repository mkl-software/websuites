package com.mkl.websuites.internal.command.impl.flow.iff;

import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;

public class BrowserCondition implements IfCondition {


    protected String requiredBrowser;

    protected boolean negate;



    public BrowserCondition(String requiredBrowser) {
        this(requiredBrowser, false);
    }


    public BrowserCondition(String requiredBrowser, boolean reverseCondition) {
        this.requiredBrowser = requiredBrowser;
        this.negate = reverseCondition;
    }



    @Override
    public boolean isConditionMet() {

        String curentBrowser = currentBrowser();

        boolean browsersOK = requiredBrowser.equals(curentBrowser);

        return negate ? !browsersOK : browsersOK;

    }


    protected String currentBrowser() {
        return ServiceFactory.get(BrowserController.class).currentBrowser();
    }

}
