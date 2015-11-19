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
package com.mkl.websuites.itests.web.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.BeforeClass;

import com.mkl.websuites.internal.services.ServiceFactory;



@Slf4j
public abstract class ServiceBasedTest<T> {

    @BeforeClass
    public static void init() {

        try {
            ServiceFactory.init();
        } catch (Exception e) {
            log.debug("ServiceFactory already initialized, ignoring, not a problem");
        }
    }

    protected T logic() {
        return ServiceFactory.get(getServiceUnderTestClass());
    }


    @Before
    public void serviceExisting() {
        try {
            assertNotNull(ServiceFactory.get(getServiceUnderTestClass()));
        } catch (NullPointerException e) {
            fail("service not configured");
        }
    }

    protected abstract Class<T> getServiceUnderTestClass();

}
