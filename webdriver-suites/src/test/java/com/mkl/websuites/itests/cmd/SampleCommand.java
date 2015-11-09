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

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.command.CommandDescriptor;



@Slf4j
@CommandDescriptor(name = "sample", argumentTypes = {String.class})
public class SampleCommand implements Command {


    private String arg;


    public SampleCommand(String argument) {
        this.arg = argument;
        log.debug("constructor for command invoked with argument: " + argument);
    }


    @Override
    public void run() {

        log.debug("running command with argument: " + arg);

    }

}
