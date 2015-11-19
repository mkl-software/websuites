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
package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.junit.Assert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckCommand;


@CommandDescriptor(name = "~check", argumentTypes = String.class)
public class NegCheckCommand extends CheckCommand {

    public NegCheckCommand(String elemement) {
        super(elemement);
    }


    public NegCheckCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }



    @Override
    protected void runCommandWithParameters() {

        boolean fail = false;
        try {
            super.runCommandWithParameters();
            fail = true;

        } catch (AssertionError e) {
            // if "no element found", then passed, else propagate:
            if (!e.getMessage().contains("No element found")) {
                throw e;
            }

        }

        if (fail) {
            localFail("Element with selector [" + by + "] was found on the page, but shouldn't be.");
        }
    }


    // can't override fail from OperationOnWebElement because
    // there it must fail hard to be detected as "correct" test pass.
    protected void localFail(String string) {
        Assert.fail(string);
    }


}
