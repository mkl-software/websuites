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
package com.mkl.websuites.internal.command.impl.key;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.OperationOnWebElement;


@CommandDescriptor(name = "type", argumentTypes = {String.class, String.class})
public class TypeTextCommand extends OperationOnWebElement {


    private String textToType;


    public TypeTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    public TypeTextCommand(String element, String textToType) {
        super(element);
        super.elementSelector = element;
        this.textToType = textToType;
    }


    @Override
    protected void doOperationOnElement(WebElement elem) {
        String text = populateStringWithProperties(textToType);
        elem.sendKeys(text);

    }



}
