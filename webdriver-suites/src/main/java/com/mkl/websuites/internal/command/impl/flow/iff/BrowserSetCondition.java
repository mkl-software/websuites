package com.mkl.websuites.internal.command.impl.flow.iff;

public class BrowserSetCondition extends BrowserCondition {


    private static final String DELIMITTER = ",";


    public BrowserSetCondition(String requiredBrowser) {
        super(requiredBrowser);
    }

    public BrowserSetCondition(String requiredBrowser, boolean reversedCondition) {
        super(requiredBrowser, reversedCondition);
    }


    @Override
    public boolean isConditionMet() {

        String[] browsers = requiredBrowser.split(DELIMITTER);

        for (String browser : browsers) {
            super.requiredBrowser = browser;
            if (!negate && super.isConditionMet()) { // "is in [...]"
                return true;
            }
            if (negate && !super.isConditionMet()) { // "is not in [...]"
                return false;
            }
        }

        return !negate ? false : true;
    }

}
