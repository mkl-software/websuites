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
package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.config.WebSuitesConfig;


@CommandDescriptor(name = "goto", argumentTypes = {String.class})
public class GotoCommand extends BaseCommand {


    private String address;

    public GotoCommand(String address) {
        this.address = address;
    }

    @Override
    protected void runStandardCommand() {

        if (address.startsWith("/")) {
            // relative address:
            // TODO: use a service to apply normalizePath logic
            address = WebSuitesConfig.get().site().basePath() + address;

        } else {
            if (!address.startsWith("http:")) {
                address = "http://" + address;
            }
        }

        browser.get(address);

    }

}
