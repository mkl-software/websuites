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
package com.mkl.websuites.itests.cmd;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.itests.web.core.CommandInvocationVerifier;


@CommandDescriptor(name = "internal-test", argumentTypes = String.class)
public class UnitTestVerificationCommand extends BaseCommand {

    private String message;

    public UnitTestVerificationCommand(String message) {
        super();
        this.message = message;
    }

    @Override
    protected void runStandardCommand() {

        CommandInvocationVerifier.getInstance().verifyInvocation(populateStringWithProperties(message));

    }


    @Override
    public String toString() {
        return "internal-test (" + message + ")";
    }

}
