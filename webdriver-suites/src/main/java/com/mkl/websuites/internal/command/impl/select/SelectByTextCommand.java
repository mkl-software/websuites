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
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;

@CommandDescriptor(name = "selectByText", argumentTypes = {String.class, String.class})
public class SelectByTextCommand extends OperationOnWebElement {

    private static final String TEXT_PARAM = "text";


    public SelectByTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public SelectByTextCommand(final String selector, final String text) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
                put(TEXT_PARAM, text);
            }
        });
    }

    @Override
    protected void doOperationOnElement(WebElement elem) {

        if (!CommandUtils.checkIfElementIsSelect(elem)) {
            fail(String.format("Element picked by selector '%s' must be a SELECT, but is '%s'", by, elem.getTagName()));
        }

        String text = parameterMap.get(TEXT_PARAM);

        Select select = new Select(elem);

        doSelect(text, select);
    }

    protected void doSelect(String text, Select select) {
        select.selectByVisibleText(text);
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        List<SchemaValidationRule> parentValidationRules = super.defineValidationRules();

        for (SchemaValidationRule schemaValidationRule : parentValidationRules) {
            schemaValidationRule.addMandatoryElements(TEXT_PARAM);
        }

        return parentValidationRules;
    }

}
