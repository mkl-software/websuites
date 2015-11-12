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

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.BaseCommand;

public abstract class AbstractCheck extends BaseCommand {



    /**
     * Template method which decomposes asserting logic to enable elegant way of running soft and
     * negated assertions.
     */
    @Override
    protected void runStandardCommand() {

        Object[] args = getAssertionsParameters();

        AbstractAssert<?, ?> assertion = buildAssertion(args);

        runAssertion(assertion, args);

    }


    protected abstract Object[] getAssertionsParameters();

    /**
     * Overridden by either hard or soft assertion. Could be more elegant unless AssertJ API which
     * isn't consistent for hard and soft assertions...
     * 
     * @param args Params that undergo an assertion
     */
    protected abstract AbstractAssert<?, ?> buildAssertion(Object... args);

    protected abstract void runAssertion(AbstractAssert<?, ?> assertion, Object... args);

    /**
     * Convenience method invoked many times for soft assertions. Again, AssertJ API...
     * 
     * @param args
     * @return
     */
    protected AbstractAssert<?, ?> soft(Object... args) {
        return getSoftAssertion().assertThat((String) args[0]);
    }

}
