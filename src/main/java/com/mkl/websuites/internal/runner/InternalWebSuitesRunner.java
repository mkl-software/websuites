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
package com.mkl.websuites.internal.runner;

import lombok.extern.slf4j.Slf4j;

import org.junit.internal.runners.JUnit38ClassRunner;

import com.mkl.websuites.WebSuitesRunner;


@Slf4j
public class InternalWebSuitesRunner extends JUnit38ClassRunner {


    public InternalWebSuitesRunner(Class<? extends WebSuitesRunner> klass) throws Throwable {

        super(new WebSuitesRunner(klass).defineMasterSuite());

        log.debug("custom runner initialized for runner: " + klass);

    }


}
