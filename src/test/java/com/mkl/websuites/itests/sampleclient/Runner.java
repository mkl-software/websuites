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
package com.mkl.websuites.itests.sampleclient;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.SiteConfig;
import com.mkl.websuites.itests.web.BrowsersConfig;



@WebSuites(browsers = "${env.testBrowser}", scenarios = @ScenarioFile("src/test/resources/client/temp.scn"),
// classes = @TestClass(SampleStandaloneTest.class),
        browserResusableConfiguration = BrowsersConfig.class, site = @SiteConfig(host = "localhost", port = 8080,
                basePath = "/", waitTimeout = 5, doNotCloseBrowserAtTheEnd = false)
// extension = @Extension(serviceOverrides = @Service(
// service = BrowserController.class,
// implementation = MyBrowserController.class
// )
// )
)
public class Runner extends WebSuitesRunner {
}
