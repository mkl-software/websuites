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
package com.mkl.websuites;

import static junitparams.JUnitParamsRunner.$;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.extern.slf4j.Slf4j;
import mockit.MockUp;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mkl.websuites.internal.CommonUtils;



@Slf4j
@RunWith(JUnitParamsRunner.class)
public class MultiBrowserTestCaseTest {



    private static MultiBrowserTestCase logic;



    @BeforeClass
    public static void init() {

        logic = new MockUp<MultiBrowserTestCase>() {}.getMockInstance();
        log.debug("JMockit mock " + logic.getClass() + " initialized, JMockit configured properly");
    }



    @SuppressWarnings("unused")
    private Object[] parametersForTestNormalizedPath() {
        return $(
                $("http", "google.com", 80, "", "http://google.com"),
                $("http", "google.com", 80, "/", "http://google.com/"),
                $("http", "google.com", 80, "//", "http://google.com/"),
                $("http", "google.com", 80, "resource", "http://google.com/resource"),
                $("http", "google.com", 1090, "", "http://google.com:1090"),
                $("http", "google.com", 1090, "/", "http://google.com:1090/"),
                $("https", "google.com", 1090, "/", "https://google.com:1090/"),
                $("file", "/home/root/pages/index.html", 80, "", "file:///home/root/pages/index.html"),
                $("http", "google.com", 80, "some/path//path", "http://google.com/some/path/path"));
    }


    @Test
    @Parameters
    public void testNormalizedPath(String protocol, String host, int port, String path, String expected) {

        String normalized = CommonUtils.normalizeUrlPath(protocol, host, port, path);

        Assert.assertEquals(expected, normalized);
    }



}
