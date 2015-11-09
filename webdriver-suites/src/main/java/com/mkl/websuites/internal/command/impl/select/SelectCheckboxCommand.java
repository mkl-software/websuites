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

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;



@CommandDescriptor(name = "selectCheckbox", argumentTypes = {String.class})
public class SelectCheckboxCommand extends OperationOnWebElement {



    public SelectCheckboxCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public SelectCheckboxCommand(final String selector) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
            }
        });
    }

    @Override
    protected void doOperationOnElement(WebElement elem) {

        if (!CommandUtils.checkIfElementIsCheckBox(elem)) {
            fail(String.format("Element picked by selector '%s' must be an INPUT[type=CHECKBOX], but is '%s'", by,
                    elem.getTagName() + "[type=" + elem.getAttribute("type") + "]"));
        }

        selectCheckbox(elem);

    }

    protected void selectCheckbox(WebElement elem) {
        if (!elem.isSelected()) {
            elem.click();
        }
    }

}
