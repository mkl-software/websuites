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

import lombok.extern.slf4j.Slf4j;
import mockit.Deencapsulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public class JettyBasedTest {


    public static final int PORT_NUMER = 90;

    private static Server server;


    protected int calculateExpectedRunCount(int underlyingTestCount, int browserCount) {

        // each test for every browser, + one browser startup test per browser + one close-up test
        return (underlyingTestCount + 1) * browserCount + 1;
    }


    @BeforeClass
    public static void setupTestEnv() {

        // reset underlying ServiceFactory
        Deencapsulation.setField(ServiceFactory.class, "isInitialized", false);

        // reset WSUP:
        Deencapsulation.setField(WebSuitesUserProperties.class, "instance", null);

        server = new Server(PORT_NUMER);

        server.setStopAtShutdown(true);

        WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setWar("src/test/resources/webapp");
        server.addHandler(bb);

        try {

            log.debug("startig jetty server");
            server.start();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    @AfterClass
    public static void shutdownServer() {

        try {

            log.debug("stopping jetty server");
            server.stop();
            server.join();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }



    // for manual server start:
    public static void main(String[] args) {

        setupTestEnv();
    }
}
