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

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.BooleanAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTextMatchingCommand;


/**
 * See limitation for non-negated version.
 * 
 * @author klosinskim
 *
 */
@CommandDescriptor(name = "~checkTextMatching", argumentTypes = String.class)
public class NegCheckTextMatchingCommand extends CheckTextMatchingCommand {

    public NegCheckTextMatchingCommand(String regex) {
        super(regex);
    }


    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {

        ((BooleanAssert) assertion).overridingErrorMessage(
                "The regular expression '%s' matched a text on the page, but it shoudn't. ", regex).isFalse();
    }

}
