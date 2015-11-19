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
package com.mkl.websuites.internal.command.impl.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.command.ParameterizedCommand;

@Slf4j
public abstract class ControlFlowHandler extends ParameterizedCommand {


    protected List<Command> nestedCommands = new ArrayList<Command>();

    public ControlFlowHandler(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    public ControlFlowHandler() {
        // for compatibility only, control flow command will only take maps as parameters:
        this(new HashMap<String, String>());
        log.warn("Control flow handlers may not use default constructors, "
                + "please use Map<String, String> constructor instead.");
    }

    @Override
    protected void runStandardCommand() {
        runCommandWithParameters();
    }


    public void setNestedCommands(List<Command> nestedCommands) {
        this.nestedCommands = nestedCommands;
    }


    public List<Command> getNestedCommands() {
        return nestedCommands;
    }



}
