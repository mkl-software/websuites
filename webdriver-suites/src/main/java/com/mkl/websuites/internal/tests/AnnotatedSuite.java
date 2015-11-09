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

import java.util.ArrayList;
import java.util.List;

import com.mkl.websuites.internal.WebSuitesException;

import junit.framework.Test;
import lombok.extern.slf4j.Slf4j;


// TODO: is it really needed??
@Slf4j
public class AnnotatedSuite extends MultiBrowserSuite {



    @Override
    protected List<Test> defineTests() {

        StandaloneSuite config = this.getClass().getAnnotation(StandaloneSuite.class);

        if (config == null) {

            log.error("missing SuiteClasses annotation");

            throw new WebSuitesException("Missing \"StandaloneSuite\" annotation on suite class :"
                    + getClass().getName() + ". Add the annotation and defined test suites there.");
        }


        Class<? extends Test>[] suiteClasses = config.suite();


        List<Test> result = new ArrayList<Test>();

        for (Class<? extends Test> suiteClass : suiteClasses) {

            try {
                Test dynamicSuite = suiteClass.newInstance();

                result.add(dynamicSuite);

            } catch (InstantiationException | IllegalAccessException | SecurityException e) {

                log.error("error while creating test suite for " + this.getClass().getName() + ": "
                        + e.getLocalizedMessage());

                e.printStackTrace();
            }
        }

        return result;
    }

}
