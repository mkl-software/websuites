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
package com.mkl.websuites.itests.sampleclient.ext;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.browser.StandardBrowserController;


@Slf4j
public class MyBrowserController extends StandardBrowserController {

    private static MyBrowserController instance = new MyBrowserController();

    public static MyBrowserController getInstance() {
        return instance;
    }


    @Override
    public String getBrowserDisplayName(String currentBrowser) {
        log.debug("custom getDisplayName()");
        return "{custo} " + super.getBrowserDisplayName(currentBrowser);
    }

}
