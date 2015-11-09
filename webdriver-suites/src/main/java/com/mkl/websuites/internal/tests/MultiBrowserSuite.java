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
package com.mkl.websuites.internal.tests;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;


public abstract class MultiBrowserSuite extends TestSuite {

    protected Object[] genericParams;

    public MultiBrowserSuite(Object... params) {

        genericParams = params;

        for (Test test : defineTests()) {

            // TODO: might be some validation here

            // if (test instanceof MultiBrowserSuite || test instanceof MultiBrowserTestCase) {
            //
            // addTest(test);
            //
            // } else {
            //
            // log.error("trying to add non-supported test case");
            //
            // throw new WebSuitesException("Trying to add non-supported test case with class "
            // + test.getClass().getName() + ", only MultiBrowserSuite or "
            // + "MultiBrowserTestCase are supported");
            //
            // }

            addTest(test);
        }
    }


    @Override
    public String getName() {

        return this.getClass().getName();
    }


    protected abstract List<Test> defineTests();

}
