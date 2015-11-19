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
package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;



@CommandDescriptor(name = "checkAttributeValueContaining", argumentTypes = {String.class})
public class CheckAttributeValueContainingCommand extends CheckAttributeNameCommand {

    public CheckAttributeValueContainingCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected String getPredicateString() {
        return "arguments[0].attributes[i].value != null && "
                + "arguments[0].attributes[i].value.indexOf(arguments[1]) != -1";
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage(
                "The attribute with value containing '%s' is expected to appear somewhere on the page "
                        + "(in any element)", attribute).isEqualTo("OK");
    }

}
