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
package com.mkl.websuites.internal.command.impl.select;

import java.util.Map;

import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "deselectByValue", argumentTypes = {String.class, String.class})
public class DeselctByValueCommand extends SelectByValueCommand {

    public DeselctByValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public DeselctByValueCommand(String selector, String value) {
        super(selector, value);
    }


    @Override
    protected void doSelect(String value, Select select) {
        select.deselectByValue(value);
    }
}
