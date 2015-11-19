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

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "selectByTextContaining", argumentTypes = {String.class, String.class})
public class SelectByTextContainingCommand extends SelectByTextCommand {

    public SelectByTextContainingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SelectByTextContainingCommand(String selector, String text) {
        super(selector, text);
    }


    @Override
    protected void doSelect(String text, Select select) {
        List<WebElement> options = select.getOptions();
        for (int i = 0; i < options.size(); i++) {
            WebElement option = options.get(i);
            if (option.getText().contains(text)) {
                select(select, i);
            }
        }
    }

    protected void select(Select select, int index) {
        select.selectByIndex(index);
    }
}
