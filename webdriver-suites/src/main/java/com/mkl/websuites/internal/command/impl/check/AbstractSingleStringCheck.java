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

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.StringAssert;

public abstract class AbstractSingleStringCheck extends AbstractCheck {

    @Override
    protected Object[] getAssertionsParameters() {
        return new Object[] {getStringParam()};
    }

    protected abstract String getStringParam();

    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return assertThat((String) args[0]);
    }

    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {
        runSingleStringAssertion((StringAssert) assertion, (String) args[0]);
    }

    protected abstract void runSingleStringAssertion(StringAssert assertion, String string);

}
