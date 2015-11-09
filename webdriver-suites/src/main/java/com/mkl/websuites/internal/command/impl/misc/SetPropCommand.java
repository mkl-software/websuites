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
package com.mkl.websuites.internal.command.impl.misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.ParameterizedCommand;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@Slf4j
@CommandDescriptor(name = "setProperties")
public class SetPropCommand extends ParameterizedCommand {


    public SetPropCommand() {
        this(new HashMap<String, String>());
    }


    public SetPropCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @Override
    protected void runCommandWithParameters() {

        WebSuitesUserProperties.get().populateFrom(parameterMap);
    }

    @Override
    protected void runStandardCommand() {

        log.warn("no properties to set in this command");

    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        return SchemaValidationRule.emptyValidationRules();
    }

}
