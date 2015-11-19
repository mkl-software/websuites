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
